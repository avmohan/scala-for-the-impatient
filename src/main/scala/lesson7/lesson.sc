import java.io.{FileNotFoundException, IOException}

object lesson {
  // Pattern matching
  // ================
  // Match is a bit like the switch statement in java but it's much more powerful
  //
  def eval(ch: Char) = {
    // Match is an expression, like if
    val sign = ch match {
      // No break required as in the case of switch to prevent
      // fall through
      case '+' => 1
      case '-' => -1
      // Guards can be added using guard
      case _ if Character.isDigit(ch) => Character.digit(ch, 10)
      case _ => 0 // default
    }
    sign
  }

  eval('+')
  eval('-')
  eval('a')
  eval('4')

  // The matched expression can be assigned to a variable by using
  // `case var` syntax

  // If thing after case starts with a upper case letter, it's treated
  // as a value, if it's a lower case, it treated as a variable

  // Lowercase can be treated as a value by surrounding with backticks


  // Match can be done against types instead of values by doing
  // case var: Type form. This is the preferred way to do type matching
  // instead of var.isInstanceOf[Type]
  // In the matched branch, the variable is automatically cast to the
  // correct type, unlike in the var.isInstanceof[Type], var.asInstanceOf[Type]
  // case

  // Pattern matching is also used in catching exceptions
  try {
    ///
  } catch {
    case e: FileNotFoundException => println("File not found " + e)
    case e: IOException => println("IO exception " + e)
    case e: Throwable => throw e
  }

  // Pattern matching can be used as an extractor to extract contents
  // from Tuples, Arrays, and case classes
  val pairs = for (i <- 0 to 3; j <- 0 to 3) yield (i, j)
  for (pair <- pairs) yield {
    pair match {
      case (0, _) => "0 ..."
      case (y, 0) => y + " 0"
      case _ => "neither is 0"
    }
  }


  def arrMatch(arr: Array[Int]): String = {
    arr match {
      case Array(0) => "0"
      case Array(x, y) => x + " " + y

      // _* means any number of elements
      case Array(0, _*) => "0 ..."
      case _ => "something else"
    }
  }

  assert(arrMatch(Array(0)) == "0")
  assert(arrMatch(Array(3, 4)) == "3 4")
  assert(arrMatch(Array(0, 4, 1, 4, 5, 32)) == "0 ...")
  assert(arrMatch(Array(4, 5, 32)) == "something else")


  // Extraction can also be used to define variables outside of match expressions
  val (uppercase, lowercase) = "Hello World".partition(Character.isUpperCase(_))
  assert(uppercase == "HW")
  assert(lowercase == "ello orld")

  val arr = Array(3, 4, 5, 1, 2, 3, 4, 5, 1123, 12, 21345, 1235, 1234, 6743, 622)
  val Array(first, second, _*) = arr
  assert(first == 3)
  assert(second == 4)

  // Below won't work as _* (called a sequence pattern) must be the last pattern
  //  val Array(first1, _*, second1) = arr

  // @_* syntax to bind rest to the part matched by _*
  val Array(first1, second1, rest@_*) = arr
  assert(first1 == 3)
  assert(second1 == 4)
  assert(rest.corresponds(arr.drop(2))(_ == _))


  // Case classes
  // ============
  // All construction parameters automatically becomes a val (public final fields)
  // Companion object gets an apply factory method so instances can be constructed without new
  // Automatically gets toString, equals, hashcode, unapply and copy
  // Unapply - used for extractors
  // Copy is a convenience method to copy values, changing some of the instance variables

  // sealed class means all subtypes must be defined in the same file. This assures that al subtypes are known
  sealed abstract class Amount

  case class Dollar(value: Double) extends Amount

  case class Currency(value: Double, unit: String) extends Amount


  val amounts: List[Amount] = List(Currency(12412.23, "INR"), Dollar(30), Currency(213.4, "EUR"))
  amounts.foreach {
    case Dollar(value) => println(s"Got $$$value")
    case Currency(value, "INR") => println(s"Got ₹$value")
    case Currency(value, unit) => println(s"Got $unit $value")
  }

  // Option[T] is a safe alternative to providing a value of type T or null
  // Option[T] Could be Some[T] or None, a case object that indicates that there is no value
  // eg: Map[K, V].get returns an Option[V]

  val scores = Map("Alice" -> 1, "Bob" -> 2)

  def queryScore(name: String): Unit = {
    scores.get(name) match {
      case Some(score) => println(score)
      case None => println("no score")
    }
  }

  queryScore("Bob")
  queryScore("Eve")

  // If it's possible for an operation to return no result (eg: get userdetails from db), it's better to use an
  // Option, and use Some(val)/None rather than val / null
  // This forces the caller to take care of the fact during coding rather than getting an NPE during runtime

  // Why not check for ifNull?? -> Problem is what about internal fields that could also be null, it's better to use
  // Option as opposed to multiple ifNotNull calls

  // Polymorphism vs pattern matching ??
  // ===================================
  // Polymorphism is the OOP way
  // Pattern matching is the functional way
  // In Scala, both are valid strategies:
  //    - For bounded types, use pattern matching (with a sealed trait/abstract class as the super class)
  //    - For non-bounded types, use polymorphism

}