package design_pattern.adapter.adapter4

class RectangleAdapter(private val rectangle: Rectangle): Shape {
    override fun draw() {
        rectangle.drawRectangle()
    }
}