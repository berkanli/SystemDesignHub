package org.example.design_pattern.abstract_factory.abstract_factory1

class RegularVehicleFactory : VehicleFactory {
    override fun createCar(): Car {
        return RegularCar()
    }

    override fun createBike(): Bike {
        return RegularBike()
    }
}