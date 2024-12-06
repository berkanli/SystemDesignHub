package design_pattern.adapter.adapter12

class LinuxAdapter(private val linuxWindowSystem: LinuxWindowSystem) : Window {
    override fun openWindow() {
        linuxWindowSystem.openLinuxWindow()
    }

    override fun closeWindow() {
        linuxWindowSystem.closeLinuxWindow()
    }
}