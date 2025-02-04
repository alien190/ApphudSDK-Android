package com.apphud.sdk.internal

import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.ConsumeParams
import com.android.billingclient.api.Purchase
import com.apphud.sdk.response
import java.io.Closeable

typealias ConsumeCallback = (PurchaseCallbackStatus, Purchase) -> Unit

internal class ConsumeWrapper(
    private val billing: BillingClient
) : Closeable {

    var callBack: ConsumeCallback? = null

    fun purchase(purchase: Purchase) {

        val token = purchase.purchaseToken

        val params = ConsumeParams.newBuilder()
            .setPurchaseToken(token)
            .build()
        billing.consumeAsync(params) { result, value ->
            result.response(
                "failed response with value: $value",
                { callBack?.invoke(PurchaseCallbackStatus.Error(value), purchase) },
                { callBack?.invoke(PurchaseCallbackStatus.Success(value), purchase) }
            )
        }
    }

    //Closeable
    override fun close() {
        callBack = null
    }
}