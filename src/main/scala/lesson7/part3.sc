object part3 {

  import Math.sqrt

  abstract class DoubleOption

  // This can be an object as there is only 1 instance of NoDouble
  case object NoDouble extends DoubleOption

  case class SomeDouble(value: Double) extends DoubleOption

  def inv(x: Double): DoubleOption =
    if (x == 0) NoDouble else SomeDouble(1 / x)

  val x = inv(2)
  val y = inv(0)

  def f(x: Double): DoubleOption =
    if (x <= 1) SomeDouble(sqrt(1 - x)) else NoDouble

  // f(g(x)) when defined
  def compose(f: Double => DoubleOption, g: Double => DoubleOption) = {
    def composed(x: Double): DoubleOption = {
      g(x) match {
        case v: SomeDouble => f(v.value)
        case NoDouble => NoDouble
      }
    }

    composed _
  }

  // Same thing with currying
  def compose1(f: Double => DoubleOption, g: Double => DoubleOption)(
    x: Double) = {
    g(x) match {
      case v: SomeDouble => f(v.value)
      case NoDouble => NoDouble
    }
  }

  // Same thing generalized for composing more than 2 functions
  def compose2(fs: (Double => DoubleOption)*)(x: Double) = {
    fs.foldRight(SomeDouble(x): DoubleOption) {
      case (f, s: SomeDouble) => f(s.value)
      case _ => NoDouble
    }
  }

  compose(inv, f)(0.5)
  compose1(inv, f)(0.5)
  compose2(inv, f)(0.5)

  compose2(inv, inv, inv)(0.5)

  def isEmpty(o: DoubleOption) = o match {
    case NoDouble => true
    case _: SomeDouble => false
  }


  def get(o: DoubleOption) = o match {
    case NoDouble => throw new NoSuchElementException("get on NoDouble")
    case o: SomeDouble => o.value
  }

  val a = inv(0)
  val b = inv(2)
  isEmpty(a)
//  get(a)

  isEmpty(b)
  get(b)
}
