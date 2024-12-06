package design_pattern.adapter.adapter12

class MacOSAdapter(private val macOSWindowSystem: MacOSWindowSystem) : Window {
    override fun openWindow() {
        macOSWindowSystem.openMacWindow()
    }

    override fun closeWindow() {
        macOSWindowSystem.closeMacWindow()
    }
}
