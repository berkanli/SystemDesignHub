package org.example.design_pattern.abstract_factory.abstract_factory1

fun main() {
    val regularFactory: VehicleFactory = RegularVehicleFactory()
    val electricFactory: VehicleFactory = ElectricVehicleFactory()

    val regularCar: Car = regularFactory.createCar()
    regularCar.drive()

    val electricBike: Bike = electricFactory.createBike()
    electricBike.ride()
}