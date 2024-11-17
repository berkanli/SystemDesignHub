package system_design.order_management_system.lld

import system_design.order_management_system.lld.model.Payment

interface IPaymentGateway {
    fun charge(userId: String, totalAmount: Double): Payment
    fun refund(orderId: String, totalAmount: Double): Boolean
}