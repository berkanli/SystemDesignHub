package design_pattern.adapter.adapter12

class WindowsAdapter(
    private val windowsWindowSystem: WindowsWindowSystem
) : Window{
    override fun openWindow() {
        windowsWindowSystem.openWinWindow()
    }

    override fun closeWindow() {
        windowsWindowSystem.closeWinWindow()
    }
}