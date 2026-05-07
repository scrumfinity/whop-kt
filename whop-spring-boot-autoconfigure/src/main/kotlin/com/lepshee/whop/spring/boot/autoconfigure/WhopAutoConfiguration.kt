package com.lepshee.whop.spring.boot.autoconfigure

import com.lepshee.whop.WhopClient
import com.lepshee.whop.WhopClientConfig
import com.lepshee.whop.core.WhopHttpTransport
import com.lepshee.whop.transport.KtorWhopHttpTransport
import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean

/** Spring Boot auto-configuration for the Whop Kotlin SDK. */
@AutoConfiguration
@ConditionalOnClass(WhopClient::class)
@EnableConfigurationProperties(WhopProperties::class)
class WhopAutoConfiguration {
    /** Creates the SDK configuration from `whop.*` properties. */
    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "whop", name = ["api-key"])
    fun whopClientConfig(properties: WhopProperties): WhopClientConfig =
        WhopClientConfig(
            apiKey = requireNotNull(properties.apiKey) { "whop.api-key is required to create a WhopClient." },
            baseUrl = properties.baseUrl,
            appId = properties.appId,
            webhookSecret = properties.webhookSecret,
            timeoutMillis = properties.timeout.toMillis(),
            maxRetries = properties.maxRetries,
        )

    /** Provides the default Ktor transport when applications do not define their own transport bean. */
    @Bean
    @ConditionalOnBean(WhopClientConfig::class)
    @ConditionalOnMissingBean
    fun whopHttpTransport(config: WhopClientConfig): WhopHttpTransport = KtorWhopHttpTransport(config)

    /** Provides a reusable Whop client bean. */
    @Bean
    @ConditionalOnBean(WhopClientConfig::class, WhopHttpTransport::class)
    @ConditionalOnMissingBean
    fun whopClient(
        config: WhopClientConfig,
        transport: WhopHttpTransport,
    ): WhopClient = WhopClient(config, transport)
}
