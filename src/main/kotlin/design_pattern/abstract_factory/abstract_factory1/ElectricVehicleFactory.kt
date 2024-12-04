package org.example.design_pattern.abstract_factory.abstract_factory1


class ElectricVehicleFactory : VehicleFactory {
    override fun createCar(): Car {
        return ElectricCar()
    }

    override fun createBike(): Bike {
        return ElectricBike()
    }
}