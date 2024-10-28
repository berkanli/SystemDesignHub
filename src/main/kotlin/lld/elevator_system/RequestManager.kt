package lld.elevator_system

import java.util.PriorityQueue
import kotlin.math.abs

class RequestManager(private val maxFloor: Int) {
    private val internalRequests = PriorityQueue<Int> { a, b ->
        val diffA = abs(a)
        val diffB = abs(b)
        diffA - diffB
    }

    fun addRequest(floor: Int, currentFloor: Int){
        if (floor in 0..maxFloor){
            internalRequests.add(floor - currentFloor)
        }
    }

    fun getNextRequest(): Int?{
        return internalRequests.poll()
    }

    fun hasRequest(): Boolean{
        return internalRequests.isNotEmpty()
    }
}