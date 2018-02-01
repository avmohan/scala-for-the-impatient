
package lesson4
object lesson {


  // Class parameters can be val or var
  class Point(val x: Double, val y: Double) {

    // Auxiliary constructor
    // In scala, auxiliary constructors should necessarily call the primary constructor
    // It can't directly call super class constructor
    def this() = this(0, 0)

    // Generates a new moved Point, and does not mutate the current instance
    def move(dx: Double, dy: Double) = new Point(x + dx, y + dy)

    /**
      * Distance of this point from origin
      *
      * @return distance from origin
      */
    def distanceFromOrigin = distanceFromPoint(Point.origin)

    /**
      * Distance of this point from another point
      *
      * @param p the other point
      * @return distance
      */
    def distanceFromPoint(p: Point) = math.sqrt(math.pow(p.x - x, 2) + math.pow(p.y - y, 2))

    // override keyword is required for overriding
    override def toString: String = f"($x, $y)" // Formatted string interpolation using f"" syntax


    // Operator overloading !!
    def *(sf: Int) = new Point(x * sf, y * sf)

  }

  // Companion object of point class - used to implement static methods
  object Point {
    val origin = new Point(0, 0)
  }

  val p = new Point(1, 2)

  // Ok to directly acesss fields
  p.x
  p.y

  // Clients don't know if this is an instance variable or a method call
  // Uniform access principle
  p.distanceFromOrigin


  p.distanceFromPoint(p)
  p.distanceFromOrigin
  new Point(3, 4).distanceFromOrigin

  new Point(3, 4) * 3

  // Constructor executes the body of the class

  // Operator precedence is based on the first character's precedence

  // Operator associativity is towards left, unless the operator ends in a colon, in which case
  // it is right-associative


}