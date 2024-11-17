package system_design.order_management_system.lld.service

import system_design.order_management_system.lld.IPaymentGateway
import system_design.order_management_system.lld.model.Order
import system_design.order_management_system.lld.model.Payment
import system_design.order_management_system.lld.model.enums.PaymentStatus

class PaymentService(private val paymentGateway: IPaymentGateway) {

    fun processPayment(order: Order): Payment {
        println("Processing payment for order ${order.id}...")
        val payment = paymentGateway.charge(order.userId, order.totalAmount)
        if (payment.status != PaymentStatus.COMPLETED) {
            throw IllegalStateException("Payment failed for order ${order.id}")
        }
        return payment
    }

    fun refundPayment(order: Order): Boolean {
        println("Processing refund for order ${order.id}...")
        return paymentGateway.refund(order.id, order.totalAmount)
    }
}