package com.apphud.sdk.domain

import com.android.billingclient.api.SkuDetails
import com.apphud.sdk.ApphudInternal

data class ApphudProduct(
    /**
     * Product id
     * */
    var id: String?,
    /**
    Product Identifier from Google Play.
     */
    var product_id: String,
    /**
    Product name from Apphud Dashboard
     */
    var name: String?,
    /**
    Always `play_store` in Android SDK.
     */
    var store: String,
    /**
    When paywalls are successfully loaded, skuDetails model will always be present if Google Play returned model for this product id.
    getPaywalls method will return callback only when Google Play products are fetched and mapped with Apphud products.
    May be `null` if product identifier is invalid, or product is not available in Google Play.
     */
    var skuDetails: SkuDetails?,
    /**
    Product Identifier from Paywalls.
     */
    var paywall_id: String?
) {
    internal fun findPaywallId(): String? {
        ApphudInternal.paywalls.forEach { paywall ->
            paywall.products?.forEach { product ->
                if (product.product_id == product_id) {
                    return paywall.id
                }
            }
        }
        return null
    }
}
