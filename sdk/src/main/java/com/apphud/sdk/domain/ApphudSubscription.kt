package com.apphud.sdk.domain


data class ApphudSubscription(
    val status: ApphudSubscriptionStatus,
    val productId: String,
    val expiresAt: Long,
    val startedAt: Long?,
    val cancelledAt: Long?,
    val isInRetryBilling: Boolean,
    val isAutoRenewEnabled: Boolean,
    val isIntroductoryActivated: Boolean,
    val kind: ApphudKind,
    val groupId: String
) {

    fun isActive() = when (status) {
        ApphudSubscriptionStatus.TRIAL,
        ApphudSubscriptionStatus.INTRO,
        ApphudSubscriptionStatus.PROMO,
        ApphudSubscriptionStatus.REGULAR,
        ApphudSubscriptionStatus.GRACE -> true
        else                           -> false
    }
}