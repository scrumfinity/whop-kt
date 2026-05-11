package dev.lepshee.whop;

import dev.lepshee.whop.blocking.WhopBlockingClient;
import dev.lepshee.whop.models.payments.Payment;
import dev.lepshee.whop.models.promocodes.PromoCode;

final class WhopBlockingClientJavaInteropCompileTest {
    Payment retrievePaymentWithoutKotlinDefaults(WhopBlockingClient whop) {
        return whop.getPayments().retrieve("pay_123");
    }

    PromoCode retrieveGeneratedPromoCodeResource(WhopBlockingClient whop) {
        return whop.getPromoCodes().retrieve("promo_123");
    }
}
