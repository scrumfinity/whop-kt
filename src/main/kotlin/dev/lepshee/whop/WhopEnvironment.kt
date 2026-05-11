package dev.lepshee.whop

/** Known Whop API environments. */
enum class WhopEnvironment(
    val baseUrl: String,
) {
    /** Production Whop REST API. */
    Production("https://api.whop.com/api/v1"),
}
