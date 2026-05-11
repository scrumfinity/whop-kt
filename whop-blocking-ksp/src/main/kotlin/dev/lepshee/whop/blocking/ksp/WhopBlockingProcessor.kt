package dev.lepshee.whop.blocking.ksp

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.Dependencies
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSDeclaration
import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import com.google.devtools.ksp.symbol.KSPropertyDeclaration
import com.google.devtools.ksp.symbol.KSType
import com.google.devtools.ksp.symbol.Modifier
import com.google.devtools.ksp.symbol.Origin
import com.google.devtools.ksp.validate
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.ParameterSpec
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeName
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.ksp.addOriginatingKSFile
import com.squareup.kotlinpoet.ksp.toClassName
import com.squareup.kotlinpoet.ksp.toTypeName
import com.squareup.kotlinpoet.ksp.writeTo

class WhopBlockingProcessor(
    private val codeGenerator: CodeGenerator,
    private val logger: KSPLogger,
) : SymbolProcessor {
    private var generated = false

    override fun process(resolver: Resolver): List<KSDeclaration> {
        if (generated) return emptyList()

        val whopClient =
            resolver.getClassDeclarationByName(resolver.getKSNameFromString(WHOP_CLIENT))
                ?: return emptyList()
        if (!whopClient.validate()) return listOf(whopClient)

        val resourceClasses =
            resolver
                .getAllFiles()
                .asSequence()
                .flatMap { it.declarations }
                .filterIsInstance<KSClassDeclaration>()
                .filter { it.packageName.asString() == RESOURCES_PACKAGE }
                .filter { it.simpleName.asString().endsWith("Resource") }
                .filter { it.origin != Origin.SYNTHETIC }
                .toList()

        if (resourceClasses.isEmpty()) {
            logger.info("No source Whop resource classes found; skipping blocking wrapper generation.")
            return emptyList()
        }

        val resourcesByQualifiedName = resourceClasses.associateBy { it.qualifiedName?.asString() }
        val clientResources = whopClient.resourceProperties(resourcesByQualifiedName)
        if (clientResources.isEmpty()) {
            logger.warn("No WhopClient resource properties found for blocking wrapper generation.", whopClient)
            return emptyList()
        }

        val dependencies = Dependencies(aggregating = true, sources = resolver.getAllFiles().toList().toTypedArray())
        generateClient(whopClient, clientResources, dependencies)
        resourceClasses.forEach { generateResourceWrapper(it, dependencies) }

        generated = true
        return emptyList()
    }

    private fun generateClient(
        whopClient: KSClassDeclaration,
        clientResources: List<ResourceProperty>,
        dependencies: Dependencies,
    ) {
        val whopClientClass = ClassName("dev.lepshee.whop", "WhopClient")
        val configClass = ClassName("dev.lepshee.whop", "WhopClientConfig")
        val transportClass = ClassName("dev.lepshee.whop.core", "WhopHttpTransport")
        val closeableClass = ClassName("java.io", "Closeable")
        val generatedClientClass = ClassName(BLOCKING_PACKAGE, "WhopBlockingClient")

        val clientType =
            TypeSpec
                .classBuilder(generatedClientClass)
                .addKdoc(
                    "Blocking facade for Java and Spring MVC applications that cannot call suspend functions directly.\n\n" +
                        "Prefer %T in coroutine-aware Kotlin applications. This generated wrapper delegates to the underlying coroutine client.",
                    whopClientClass,
                ).addSuperinterface(closeableClass)
                .primaryConstructor(
                    FunSpec
                        .constructorBuilder()
                        .addParameter("client", whopClientClass)
                        .build(),
                ).addProperty(
                    PropertySpec
                        .builder("client", whopClientClass)
                        .initializer("client")
                        .addKdoc("Underlying coroutine client for advanced operations not wrapped by this facade.")
                        .build(),
                ).addFunction(
                    FunSpec
                        .constructorBuilder()
                        .addParameter("config", configClass)
                        .callThisConstructor(CodeBlock.of("%T(config)", whopClientClass))
                        .build(),
                ).addFunction(
                    FunSpec
                        .constructorBuilder()
                        .addParameter("config", configClass)
                        .addParameter("transport", transportClass)
                        .callThisConstructor(CodeBlock.of("%T(config, transport)", whopClientClass))
                        .build(),
                )

        clientResources.forEach { resource ->
            clientType.addProperty(
                PropertySpec
                    .builder(resource.name, blockingClassName(resource.resourceClass))
                    .initializer("%T(client.%L)", blockingClassName(resource.resourceClass), resource.name)
                    .addKdoc("Blocking %L operations.", humanizeResourceName(resource.name))
                    .build(),
            )
        }

        clientType.addFunction(
            FunSpec
                .builder("close")
                .addModifiers(KModifier.OVERRIDE)
                .addStatement("client.close()")
                .build(),
        )
        whopClient.containingFile?.let { clientType.addOriginatingKSFile(it) }

        val extension =
            FunSpec
                .builder("blocking")
                .receiver(whopClientClass)
                .returns(generatedClientClass)
                .addKdoc("Creates a blocking facade over this coroutine client.")
                .addStatement("return %T(this)", generatedClientClass)
                .build()

        FileSpec
            .builder(BLOCKING_PACKAGE, "WhopBlockingClient")
            .indent("    ")
            .addType(clientType.build())
            .addFunction(extension)
            .build()
            .writeTo(codeGenerator, dependencies)
    }

    private fun generateResourceWrapper(
        resourceClass: KSClassDeclaration,
        dependencies: Dependencies,
    ) {
        val resourceClassName = resourceClass.toClassName()
        val blockingClassName = blockingClassName(resourceClass)
        val typeBuilder =
            TypeSpec
                .classBuilder(blockingClassName)
                .addKdoc("Blocking wrapper for %T.", resourceClassName)
                .primaryConstructor(
                    FunSpec
                        .constructorBuilder()
                        .addModifiers(KModifier.INTERNAL)
                        .addParameter("delegate", resourceClassName)
                        .build(),
                ).addProperty(
                    PropertySpec
                        .builder("delegate", resourceClassName)
                        .addModifiers(KModifier.PRIVATE)
                        .initializer("delegate")
                        .build(),
                )

        resourceClass.resourceProperties(emptyMap()).forEach { nested ->
            typeBuilder.addProperty(
                PropertySpec
                    .builder(nested.name, blockingClassName(nested.resourceClass))
                    .initializer("%T(delegate.%L)", blockingClassName(nested.resourceClass), nested.name)
                    .addKdoc("Blocking %L operations.", humanizeResourceName(nested.name))
                    .build(),
            )
        }

        resourceClass.declarations
            .filterIsInstance<KSFunctionDeclaration>()
            .filter { it.isPublicSuspendFunction() }
            .forEach { function -> typeBuilder.addFunction(generateBlockingFunction(function)) }

        resourceClass.containingFile?.let { typeBuilder.addOriginatingKSFile(it) }

        FileSpec
            .builder(BLOCKING_PACKAGE, blockingClassName.simpleName)
            .indent("    ")
            .addType(typeBuilder.build())
            .build()
            .writeTo(codeGenerator, dependencies)
    }

    private fun generateBlockingFunction(function: KSFunctionDeclaration): FunSpec {
        val builder =
            FunSpec
                .builder(function.simpleName.asString())
                .returns(function.returnType!!.toTypeName())

        function.parameters.forEach { parameter ->
            val parameterName = parameter.name!!.asString()
            val parameterType = parameter.type.toTypeName()
            val parameterBuilder = ParameterSpec.builder(parameterName, parameterType)
            if (parameter.hasDefault) {
                defaultValue(parameter.type.resolve(), parameterType)?.let { parameterBuilder.defaultValue(it) }
            }
            builder.addParameter(parameterBuilder.build())
        }

        if (function.parameters.any { it.hasDefault && defaultValue(it.type.resolve(), it.type.toTypeName()) != null }) {
            builder.addAnnotation(ClassName("kotlin.jvm", "JvmOverloads"))
        }

        builder.addCode(blockingCall(function))

        return builder.build()
    }

    private fun blockingCall(function: KSFunctionDeclaration): CodeBlock {
        val parameters = function.parameters.map { it.name!!.asString() }
        val code =
            CodeBlock
                .builder()
                .add("return runBlockingWhop {\n")
                .indent()

        if (parameters.isEmpty()) {
            code.add("delegate.%L()\n", function.simpleName.asString())
        } else {
            code
                .add("delegate.%L(\n", function.simpleName.asString())
                .indent()
            parameters.forEach { parameter -> code.add("%L,\n", parameter) }
            code
                .unindent()
                .add(")\n")
        }

        return code
            .unindent()
            .add("}\n")
            .build()
    }

    private fun defaultValue(
        type: KSType,
        typeName: TypeName,
    ): CodeBlock? {
        val qualifiedName = type.declaration.qualifiedName?.asString()
        return when {
            type.isMarkedNullable -> CodeBlock.of("null")
            qualifiedName == "dev.lepshee.whop.WhopRequestOptions" -> CodeBlock.of("%T()", typeName)
            type.arguments.isEmpty() -> CodeBlock.of("%T()", typeName)
            else -> null
        }
    }

    private fun KSClassDeclaration.resourceProperties(resourcesByQualifiedName: Map<String?, KSClassDeclaration>): List<ResourceProperty> =
        declarations
            .filterIsInstance<KSPropertyDeclaration>()
            .filter { it.isPublic() }
            .mapNotNull { property ->
                val declaration = property.type.resolve().declaration as? KSClassDeclaration ?: return@mapNotNull null
                val qualifiedName = declaration.qualifiedName?.asString()
                val isResource =
                    qualifiedName?.startsWith("$RESOURCES_PACKAGE.") == true &&
                        declaration.simpleName.asString().endsWith("Resource")
                if (!isResource) return@mapNotNull null
                ResourceProperty(property.simpleName.asString(), resourcesByQualifiedName[qualifiedName] ?: declaration)
            }.toList()

    private fun KSFunctionDeclaration.isPublicSuspendFunction(): Boolean = Modifier.SUSPEND in modifiers && isPublic()

    private fun KSDeclaration.isPublic(): Boolean =
        Modifier.PRIVATE !in modifiers && Modifier.INTERNAL !in modifiers && Modifier.PROTECTED !in modifiers

    private fun blockingClassName(resourceClass: KSClassDeclaration): ClassName =
        ClassName(BLOCKING_PACKAGE, "Blocking${resourceClass.simpleName.asString()}")

    private fun humanizeResourceName(name: String): String = name.replace(Regex("([a-z])([A-Z])"), "$1 $2").lowercase()

    private data class ResourceProperty(
        val name: String,
        val resourceClass: KSClassDeclaration,
    )

    private companion object {
        const val WHOP_CLIENT = "dev.lepshee.whop.WhopClient"
        const val RESOURCES_PACKAGE = "dev.lepshee.whop.resources"
        const val BLOCKING_PACKAGE = "dev.lepshee.whop.blocking"
    }
}
