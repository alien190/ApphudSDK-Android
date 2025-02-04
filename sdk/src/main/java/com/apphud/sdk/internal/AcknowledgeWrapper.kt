package com.apphud.sdk.internal

import com.android.billingclient.api.AcknowledgePurchaseParams
import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.BillingResult
import com.android.billingclient.api.Purchase
import com.apphud.sdk.response
import java.io.Closeable

typealias AcknowledgeCallback = (PurchaseCallbackStatus, Purchase) -> Unit

internal class AcknowledgeWrapper(
    private val billing: BillingClient
) : Closeable {

    companion object {
        private const val MESSAGE = "purchase acknowledge is failed"
    }

    var callBack: AcknowledgeCallback? = null

    fun purchase(purchase: Purchase) {

        val token = purchase.purchaseToken

        if (token.isEmpty() || token.isBlank()) {
            throw IllegalArgumentException("Token empty or blank")
        }

        val params = AcknowledgePurchaseParams.newBuilder()
            .setPurchaseToken(token)
            .build()
        billing.acknowledgePurchase(params) { result: BillingResult ->
            result.response(
                MESSAGE,
                { callBack?.invoke(PurchaseCallbackStatus.Error(result.responseCode.toString()), purchase) },
                { callBack?.invoke(PurchaseCallbackStatus.Success(), purchase) }
            )
        }
    }

    //Closeable
    override fun close() {
        callBack = null
    }
}