package org.example.design_pattern.abstract_factory.abstract_factory1

interface VehicleFactory {
    fun createCar(): Car
    fun createBike(): Bike
}