package com.apphud.sdk

import com.apphud.sdk.domain.ApphudNonRenewingPurchase
import com.apphud.sdk.domain.ApphudSubscription

class ApphudRestorePurchasesResult(
    var subscriptions: List<ApphudSubscription>?,

    var purchases: List<ApphudNonRenewingPurchase>?,

    var error: ApphudError? = null
) {
    override fun toString(): String {
        return "ApphudRestorePurchasesResult(subscriptions=$subscriptions, purchases=$purchases, error=$error)"
    }
}