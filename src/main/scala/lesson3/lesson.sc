import scala.collection.mutable.ArrayBuffer

object lesson {
  var x = Array(1, 2, 3)
  val x1 = x :+= 4 // creates a new array, copying the whole of current array and appending 1 more element, 4
  val x2 = 4 +: x // creates a new array, copying the whole of current array and prepending 1 more element, 4
  x
  x1
  x2

  val y = ArrayBuffer(1, 2, 3)
  y
  y(0) = 100
  y
  y += 4 // ArrayBuffer defines a += method so += is not treated as an assignment operator here. So even if y is a val,
  // += is not an error
  // y :+= 3 // But this is an error as this is a full copy followed by assignment i.e y = y :+ 3
  val y1 = y :+ 3
  y1(0) = -1000 // y1 is a full copy of y, so this doesn't affect y
  y
  y1


  val z = List(1, 2, 3)
  z :+ 4
  4 +: z
  z


  // Maps
  val scores = Map("Alice" -> 10, "Bob" -> 3)
  scores("Bob")
  //  scores("Harry") // No such element exception
  scores.getOrElse("Harry", -1)
  scores

  scores + ("Bob" -> 9, "George" -> 17) // generates a new map without changing the original
  scores
  //  scores += ("Bob" -> 9, "George" -> 17) // error as this is an assignment and scores is a val
  var scores1 = scores
  scores1 += ("Bob" -> 9, "George" -> 17) // This is an assignment. Ok as scores1 is a var
  scores - "Alice"


  val mscores = scala.collection.mutable.Map("Alice" -> 10, "Bob" -> 25)
  //  mscores("Alice") = 13 // this is not working in worksheets due to some intellij bug :-(
  mscores += "Alice" -> 13 // Not an assignment !!


  // Tuples
  val t = (1, 3.14, "Fred") // can be different types
  val u = (1, 2, 3, 4) // can be same types
  t._2  // tuples are 1-indexed not 0-indexed.


  val (first, _, _) = t
  first
}