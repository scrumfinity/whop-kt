package dev.lepshee.whop

import dev.lepshee.whop.core.WhopHttpExecutor
import dev.lepshee.whop.core.WhopHttpTransport
import dev.lepshee.whop.oauth.WhopOAuthClient
import dev.lepshee.whop.resources.AccessTokensResource
import dev.lepshee.whop.resources.AccountLinksResource
import dev.lepshee.whop.resources.AdCampaignsResource
import dev.lepshee.whop.resources.AdGroupsResource
import dev.lepshee.whop.resources.AdsResource
import dev.lepshee.whop.resources.AffiliatesResource
import dev.lepshee.whop.resources.AiChatsResource
import dev.lepshee.whop.resources.AppBuildsResource
import dev.lepshee.whop.resources.AppsResource
import dev.lepshee.whop.resources.AuthorizedUsersResource
import dev.lepshee.whop.resources.BountiesResource
import dev.lepshee.whop.resources.CardTransactionsResource
import dev.lepshee.whop.resources.ChatChannelsResource
import dev.lepshee.whop.resources.CheckoutConfigurationsResource
import dev.lepshee.whop.resources.CompaniesResource
import dev.lepshee.whop.resources.CompanyTokenTransactionsResource
import dev.lepshee.whop.resources.ConversionsResource
import dev.lepshee.whop.resources.CourseChaptersResource
import dev.lepshee.whop.resources.CourseLessonInteractionsResource
import dev.lepshee.whop.resources.CourseLessonsResource
import dev.lepshee.whop.resources.CourseStudentsResource
import dev.lepshee.whop.resources.CoursesResource
import dev.lepshee.whop.resources.DisputeAlertsResource
import dev.lepshee.whop.resources.DisputesResource
import dev.lepshee.whop.resources.DmChannelsResource
import dev.lepshee.whop.resources.DmMembersResource
import dev.lepshee.whop.resources.EntriesResource
import dev.lepshee.whop.resources.ExperiencesResource
import dev.lepshee.whop.resources.FeeMarkupsResource
import dev.lepshee.whop.resources.FilesResource
import dev.lepshee.whop.resources.ForumPostsResource
import dev.lepshee.whop.resources.ForumsResource
import dev.lepshee.whop.resources.InvoicesResource
import dev.lepshee.whop.resources.LeadsResource
import dev.lepshee.whop.resources.LedgerAccountsResource
import dev.lepshee.whop.resources.MembersResource
import dev.lepshee.whop.resources.MembershipsResource
import dev.lepshee.whop.resources.MessagesResource
import dev.lepshee.whop.resources.NotificationsResource
import dev.lepshee.whop.resources.PaymentMethodsResource
import dev.lepshee.whop.resources.PaymentsResource
import dev.lepshee.whop.resources.PayoutAccountsResource
import dev.lepshee.whop.resources.PayoutMethodsResource
import dev.lepshee.whop.resources.PlansResource
import dev.lepshee.whop.resources.ProductsResource
import dev.lepshee.whop.resources.PromoCodesResource
import dev.lepshee.whop.resources.ReactionsResource
import dev.lepshee.whop.resources.RefundsResource
import dev.lepshee.whop.resources.ResolutionCenterCasesResource
import dev.lepshee.whop.resources.ReviewsResource
import dev.lepshee.whop.resources.SetupIntentsResource
import dev.lepshee.whop.resources.ShipmentsResource
import dev.lepshee.whop.resources.StatsResource
import dev.lepshee.whop.resources.SupportChannelsResource
import dev.lepshee.whop.resources.TopupsResource
import dev.lepshee.whop.resources.TransfersResource
import dev.lepshee.whop.resources.UsersResource
import dev.lepshee.whop.resources.VerificationsResource
import dev.lepshee.whop.resources.WebhooksResource
import dev.lepshee.whop.resources.WithdrawalsResource
import dev.lepshee.whop.transport.KtorWhopHttpTransport
import java.io.Closeable

/**
 * Entry point for the Whop Kotlin SDK.
 *
 * A client owns the configured authentication, HTTP transport, JSON handling, and resource groups.
 * Reuse one [WhopClient] for many requests instead of constructing a client per operation.
 */
class WhopClient(
    /** Static client configuration used for default authentication and transport behavior. */
    val config: WhopClientConfig,
    transport: WhopHttpTransport = KtorWhopHttpTransport(config),
) : Closeable {
    private val executor = WhopHttpExecutor(config, transport)

    /** Access-token minting operations. */
    val accessTokens: AccessTokensResource =
        AccessTokensResource(executor)

    /** App management operations. */
    val apps: AppsResource =
        AppsResource(executor)

    /** App build operations. */
    val appBuilds: AppBuildsResource =
        AppBuildsResource(executor)

    /** Ad campaign operations. */
    val adCampaigns: AdCampaignsResource =
        AdCampaignsResource(executor)

    /** Ad group operations. */
    val adGroups: AdGroupsResource =
        AdGroupsResource(executor)

    /** Ad operations. */
    val ads: AdsResource =
        AdsResource(executor)

    /** Affiliate and commission override operations. */
    val affiliates: AffiliatesResource =
        AffiliatesResource(executor)

    /** Workforce bounty operations. */
    val bounties: BountiesResource =
        BountiesResource(executor)

    /** Conversion tracking operations. */
    val conversions: ConversionsResource =
        ConversionsResource(executor)

    /** Checkout configuration operations for embedded and hosted checkout flows. */
    val checkoutConfigurations: CheckoutConfigurationsResource = CheckoutConfigurationsResource(executor)

    /** Payment operations. Payment creation/fulfillment semantics should be confirmed with webhooks. */
    val payments: PaymentsResource = PaymentsResource(executor)

    /** Product catalog operations. */
    val products: ProductsResource =
        ProductsResource(executor)

    /** Product promotion code operations. */
    val promoCodes: PromoCodesResource =
        PromoCodesResource(executor)

    /** Product experience operations. */
    val experiences: ExperiencesResource =
        ExperiencesResource(executor)

    /** Pricing plan operations. */
    val plans: PlansResource =
        PlansResource(executor)

    /** User operations, including explicit access checks. */
    val users: UsersResource = UsersResource(executor)

    /** Membership operations. */
    val memberships: MembershipsResource = MembershipsResource(executor)

    /** Webhook endpoint management operations. */
    val webhooks: WebhooksResource = WebhooksResource(executor)

    /** Company operations. */
    val companies: CompaniesResource = CompaniesResource(executor)

    /** Transfer operations. */
    val transfers: TransfersResource = TransfersResource(executor)

    /** Platform balance top-up operations. */
    val topups: TopupsResource =
        TopupsResource(executor)

    /** Ledger account withdrawal operations. */
    val withdrawals: WithdrawalsResource =
        WithdrawalsResource(executor)

    /** Refund operations. */
    val refunds: RefundsResource =
        RefundsResource(executor)

    /** Company member operations. */
    val members: MembersResource =
        MembersResource(executor)

    /** Saved payment method operations. */
    val paymentMethods: PaymentMethodsResource =
        PaymentMethodsResource(executor)

    /** Payout method operations. */
    val payoutMethods: PayoutMethodsResource =
        PayoutMethodsResource(executor)

    /** Payout account operations. */
    val payoutAccounts: PayoutAccountsResource =
        PayoutAccountsResource(executor)

    /** Payout-account verification operations. */
    val verifications: VerificationsResource =
        VerificationsResource(executor)

    /** Product review operations. */
    val reviews: ReviewsResource =
        ReviewsResource(executor)

    /** Company authorized-user operations. */
    val authorizedUsers: AuthorizedUsersResource =
        AuthorizedUsersResource(executor)

    /** Lead capture operations. */
    val leads: LeadsResource =
        LeadsResource(executor)

    /** Waitlist entry operations. */
    val entries: EntriesResource =
        EntriesResource(executor)

    /** Stats schema and query operations. */
    val stats: StatsResource =
        StatsResource(executor)

    /** File record and presigned upload operations. */
    val files: FilesResource =
        FilesResource(executor)

    /** Course student analytics operations. */
    val courseStudents: CourseStudentsResource =
        CourseStudentsResource(executor)

    /** Course content operations. */
    val courses: CoursesResource = CoursesResource(executor)

    /** Course chapter operations. */
    val courseChapters: CourseChaptersResource = CourseChaptersResource(executor)

    /** Course lesson operations. */
    val courseLessons: CourseLessonsResource = CourseLessonsResource(executor)

    /** Course lesson interaction analytics operations. */
    val courseLessonInteractions: CourseLessonInteractionsResource = CourseLessonInteractionsResource(executor)

    /** Sub-merchant hosted account link operations. */
    val accountLinks: AccountLinksResource =
        AccountLinksResource(executor)

    /** Push notification operations. */
    val notifications: NotificationsResource =
        NotificationsResource(executor)

    /** Setup intent operations. */
    val setupIntents: SetupIntentsResource =
        SetupIntentsResource(executor)

    /** Issued-card transaction operations. */
    val cardTransactions: CardTransactionsResource =
        CardTransactionsResource(executor)

    /** Company token balance transaction operations. */
    val companyTokenTransactions: CompanyTokenTransactionsResource = CompanyTokenTransactionsResource(executor)

    /** Dispute and fraud alert operations. */
    val disputeAlerts: DisputeAlertsResource = DisputeAlertsResource(executor)

    /** Payment dispute and evidence operations. */
    val disputes: DisputesResource = DisputesResource(executor)

    /** Company fee markup operations. */
    val feeMarkups: FeeMarkupsResource =
        FeeMarkupsResource(executor)

    /** Invoice creation, retrieval, and lifecycle operations. */
    val invoices: InvoicesResource =
        InvoicesResource(executor)

    /** Ledger account balance operations. */
    val ledgerAccounts: LedgerAccountsResource =
        LedgerAccountsResource(executor)

    /** Resolution center case operations. */
    val resolutionCenterCases: ResolutionCenterCasesResource =
        ResolutionCenterCasesResource(executor)

    /** Shipment tracking operations. */
    val shipments: ShipmentsResource =
        ShipmentsResource(executor)

    /** Experience chat channel operations. */
    val chatChannels: ChatChannelsResource =
        ChatChannelsResource(executor)

    /** AI chat thread operations. */
    val aiChats: AiChatsResource =
        AiChatsResource(executor)

    /** Company support channel operations. */
    val supportChannels: SupportChannelsResource =
        SupportChannelsResource(executor)

    /** Direct-message channel operations. */
    val dmChannels: DmChannelsResource =
        DmChannelsResource(executor)

    /** Direct-message member operations. */
    val dmMembers: DmMembersResource =
        DmMembersResource(executor)

    /** Message operations for chats and direct-message channels. */
    val messages: MessagesResource =
        MessagesResource(executor)

    /** Forum feed operations for experiences. */
    val forums: ForumsResource =
        ForumsResource(executor)

    /** Forum post and comment operations. */
    val forumPosts: ForumPostsResource =
        ForumPostsResource(executor)

    /** Reaction operations for messages and forum posts. */
    val reactions: ReactionsResource =
        ReactionsResource(executor)

    /** OAuth 2.1 and PKCE helper operations for user-delegated authentication. */
    val oauth: WhopOAuthClient = WhopOAuthClient(executor)

    /** Closes the underlying HTTP transport and releases network resources. */
    override fun close() {
        executor.close()
    }

    companion object {
        /**
         * Creates a client using an API key from an environment variable.
         *
         * This convenience method reads the environment once during client construction; request execution
         * does not read environment variables implicitly.
         */
        fun fromEnvironment(
            variableName: String = "WHOP_API_KEY",
            baseUrl: String = WhopEnvironment.Production.baseUrl,
        ): WhopClient {
            val apiKey =
                System.getenv(variableName)
                    ?: error("Environment variable $variableName is required to create a WhopClient.")
            return WhopClient(WhopClientConfig(apiKey = apiKey, baseUrl = baseUrl))
        }
    }
}
