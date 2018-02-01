import java.awt.geom.Ellipse2D

object part1 {

  import java.awt._

  val rect = new Rectangle(5, 10, 20, 30)
  rect.translate(10, 20)
  rect

  val ellipse = new geom.Ellipse2D.Double(5, 10, 20, 30)

  //  Below line doesn't compile as ellipse has no translate method
  //  ellipse.translate(10, 20)

  trait RectangleLike {
    def setFrame(x: Double, y: Double, w: Double, h: Double): Unit

    def getX: Double

    def getY: Double

    def getWidth: Double

    def getHeight: Double

    def translate(dx: Double, dy: Double): Unit = {
      setFrame(getX + dx, getY + dy, getWidth, getHeight)
    }
  }

  val rectangleLikeEllipse = new Ellipse2D.Double(5, 10, 20, 30) with RectangleLike
  assert((rectangleLikeEllipse.getX, rectangleLikeEllipse.getY) == (5, 10))

  // This is mixed in with the RectangleLike trait, so it has the translate method
  rectangleLikeEllipse.translate(10, 20)
  assert((rectangleLikeEllipse.getX, rectangleLikeEllipse.getY) == (15, 30))


}