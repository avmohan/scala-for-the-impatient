object lesson {

  import scala.math._

  // Functions are first class, they can be assigned to variables
  // Functions are nothing special, they're just like Int, Double, String etc
  val num = 3.14
  val fun = ceil _
  fun(num)

  // Functions can be passed to other functions
  val a1 = Array(3.14, 1.42, 2.0).map(fun)

  // Functions need not be named
  val a2 = Array(3.14, 1.42, 2.0).map((x: Double) => 3 * x)

  // Function type can be inferred at call site
  val a3 = Array(3.14, 1.42, 2.0).map(x => 3 * x)
  // This is nothing special. It's just like we can call sqrt(5) without
  // specifying the type of 5

  // Same can be written in a more concise manner as below
  val a4 = Array(3.14, 1.42, 2.0).map(_ * 3)

  // Below are equivalent
  val triple1 = (x: Double) => 3 * x

  def triple2(x: Double) = 3 * x

  // Functions that take other functions as argument is called a higher order
  // function. An example of a higher order function is map

  // We can also define higher order functions, like below
  def valueAtOneQuarter(f: Double => Double) = f(0.25)

  // f has type Double => Double. i.e. it's a function that takes a Double
  // and returns a Double

  // valueAtOneQuarter takes any Double=>Double function and finds out the value
  // of that function at 0.25
  valueAtOneQuarter(ceil)
  valueAtOneQuarter(sqrt)

  // Functions can also return other functions
  def mulBy(factor: Double) = (x: Double) => factor * x

  mulBy(2)(3) // mulBy(2) returns a [function that takes & returns a Double]

  // map
  (1 to 9).map(0.1 * _)

  // filter
  (1 to 9).filter(_ % 2 == 0)

  // reduceLeft
  (1 to 100).reduceLeft(_ * _)

  // FizzBuzz
  (1 to 20).map {
    x => {
      if (x % 15 == 0) "FizzBuzz"
      else if (x % 3 == 0) "Fizz"
      else if (x % 5 == 0) "Buzz"
      else x
    }
  }.foreach(println)

  // Closure
  // ========
  // A function has access to any variable from an enclosing scope.
  // Where is this stored when the enclosing scope goes out of scope but the function itself
  // is still in scope? => These are captured by the function so that it can be used when the
  // function is called

  val quadruple = mulBy(4)
  quadruple(3) // where is the factor 4 stored??

  val double = mulBy(2)
  double(3) // where is the factor 2 stored??

  // Each time mulBy is called, it creates a new Double => Double function instance and the value
  // of parameter variable factor of the external scope is captured in that instance.

  // Internally these function instances become objects, and the closure variables are captured in
  // the instance fields of that object

  // Currying
  // ========
  // Instead of functions that can take multiple arguments, what if all functions could take only
  // a single argument? Instead of f(a:A, b:B): C, we can have f(a:A) : ((b:B) => c: C)
  // and instead of calling f(a, b), we can call it as f(a)(b). Functions can be called partially
  // like g = f(a) and later on the rest can be called, like g(b1) and g(b2)

  // In some functional programming languages, functions can only have a single parameter. Scala
  // allows multiple argument as well as currying

  // Example
  def add(x: Int, y: Int) = x + y

  def addOneAtATime(x: Int) = (y: Int) => x + y

  assert(add(4, 6) == 10)
  assert(addOneAtATime(4)(6) == 10)

  // Scala supports some syntactic sugar for currying
  def addOneAtATime2(x: Int)(y: Int) = x + y

  assert(addOneAtATime2(4)(6) == 10)


  val a = Array("Hello", "World")
  val b = Array("hello", "world")
  a.corresponds(b)(_.equalsIgnoreCase(_))

  // Currying is useful for type inference for functions like corresponds that take functions with
  // parameterized types
  // corresponds in GenSeqLike[A] has below signature
  // corresponds[B](that: GenSeq[B])(p: (A,B) => Boolean): Boolean =
  // By the time a.corresponds(b) is called, scala already knows A & B so no need to specify them

  // If it was defined in a non-curried manner, clients would have been forced to call it as
  // a.corresponds(b, (s: String, t: String) => s.equalsIgnoreCase(t))

}