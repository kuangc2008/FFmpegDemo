package com.kc.test.design3

sealed class WaterMachineState(open val machine: WaterMachine) {
    fun turnHeating() {
        if (this !is Heating) {
            println("turn heating")
            machine.state = machine.heating
        } else {
            println("is already")
        }
    }

    fun turnCooling() {
        if (this !is Cooling) {
            println("turn cool")
            machine.state = machine.cooling
        } else {
            println("is already")
        }
    }

    fun turnOff() {
        if (this !is Off) {
            println("turn off")
            machine.state = machine.off
        } else {
            println("is off")
        }
    }
}

class Off(override val machine: WaterMachine) : WaterMachineState(machine)
class Heating(override val machine: WaterMachine) : WaterMachineState(machine)
class Cooling(override val machine: WaterMachine) : WaterMachineState(machine)


class WaterMachine (var state : WaterMachineState) {

    val off = Off(this)
    val heating = Heating(this)
    val cooling = Cooling(this)

    init {
        state = off
    }

    fun turnHeating() {
        this.state.turnHeating()
    }

    fun turnCookling() {
        this.state.turnCooling()
    }

    fun turnOff() {
        this.state.turnOff()
    }
}


enum class Moment {
    EARLY_MORNING,
    DRINKING_WATER,
    INSTANCE_NOODLES,
    AFTER_WORK
}

fun waterMachineOps(machine: WaterMachine, moment : Moment) {
    when(moment) {
        Moment.EARLY_MORNING,
        Moment.DRINKING_WATER -> when(machine.state) {
            !is Cooling -> machine.turnCookling()
        }
        Moment.INSTANCE_NOODLES -> when(machine.state) {
            !is Heating -> machine.turnHeating()
        }
        Moment.AFTER_WORK -> when(machine.state) {
            !is Off -> machine.turnOff()
        }
    }
}