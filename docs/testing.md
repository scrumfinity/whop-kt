# Testing

The SDK test suite uses Ktor `MockEngine`; default tests do not require real Whop credentials.
The core SDK and optional Spring Boot modules target Java 17+ for Spring Boot 3 compatibility.

```bash
./gradlew test
./gradlew build
```

## What is covered

- Auth and idempotency headers
- Request paths and JSON bodies
- Error parsing and request ID preservation
- Cursor pagination
- Standard Webhooks verification
- OAuth + PKCE helpers
- Iframe token verification with caller-supplied keys
- User access-check response parsing

## ABI and API documentation checks

The build runs Kotlin ABI validation through `checkKotlinAbi` to catch accidental public API changes.

```bash
./gradlew checkKotlinAbi
```

When an intentional public API change is made, update the checked-in baseline:

```bash
./gradlew updateKotlinAbi
```

Generate API documentation with Dokka:

```bash
./gradlew dokkaGenerate
```

Publishing uses a Dokka-backed `javadocJar` so Maven consumers receive a Javadoc-format documentation artifact.

## Integration tests

Integration tests are opt-in and are skipped by default. Run them only with explicit credentials:

```bash
WHOP_INTEGRATION_TESTS=true \
WHOP_API_KEY=... \
WHOP_COMPANY_ID=biz_... \
./gradlew integrationTest
```

The integration test task is skipped unless `WHOP_INTEGRATION_TESTS=true`, so `./gradlew test` and `./gradlew build` remain credential-free.
