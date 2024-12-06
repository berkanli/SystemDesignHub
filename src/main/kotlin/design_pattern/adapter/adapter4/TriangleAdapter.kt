package design_pattern.adapter.adapter4

class TriangleAdapter(private val triangle: Triangle): Shape{
    override fun draw() {
        triangle.drawTriangle()
    }
}