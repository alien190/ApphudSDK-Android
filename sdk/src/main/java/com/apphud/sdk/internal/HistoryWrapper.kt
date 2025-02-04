package com.apphud.sdk.internal

import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.PurchaseHistoryRecord
import com.apphud.sdk.response
import java.io.Closeable

typealias PurchaseHistoryListener = (List<PurchaseHistoryRecord>) -> Unit

internal class HistoryWrapper(
    private val billing: BillingClient
) : Closeable {

    var callback: PurchaseHistoryListener? = null

    fun queryPurchaseHistory(@BillingClient.SkuType type: SkuType) {
        billing.queryPurchaseHistoryAsync(type) { result, purchases ->
            result.response("failed restore purchases") {
                callback?.invoke(purchases ?: emptyList())
            }
        }
    }

    override fun close() {
        callback = null
    }
}