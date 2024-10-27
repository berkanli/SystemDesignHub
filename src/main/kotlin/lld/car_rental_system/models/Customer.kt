package lld.car_rental_system.models

class Customer(
    id: String,
    name: String,
    email: String,
    phone: String,
    val drivingLicense: String
): User(id, name, email, phone)