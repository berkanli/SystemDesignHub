package design_pattern.adapter.adapter4

fun main() {
    val shapeDrawer = ShapeDrawer()

    val circle = Circle()
    shapeDrawer.drawShape(circle)

    val rectangleAdapter = RectangleAdapter(Rectangle())
    shapeDrawer.drawShape(rectangleAdapter)

    val triangleAdapter = TriangleAdapter(Triangle())
    shapeDrawer.drawShape(triangleAdapter)
}