object lesson {
  // For comprehensions

  // Equivalent expressions
  for (i <- 1 to 10) println(i)
  (1 to 10).foreach(println)
  1 to 10 foreach println

  // Equivalent expressions
  for (ch <- "Hello") println(ch)
  "Hello".foreach(println)
  "Hello" foreach println

  // Equivalent expressions
  for (i <- 1 to 3; j <- 1 to 3 if i != j) yield (i, j)
  (1 to 3).flatMap(i => (1 to 3).withFilter(j => i != j).map(j => (i, j)))
  (1 to 3).flatMap {
    i =>
      (1 to 3)
        .filter(j => i != j)
        .map(j => (i, j))
  }

  def abs(x: Double) = if (x < 0) -x else x

  @scala.annotation.tailrec
  def factorial(x: Int, acc: BigInt = 1): BigInt = {
    if (x == 0) acc
    else factorial(x - 1, x * acc)
  }

  def factorial2(x: Int) = (1 to x).product

  abs(-1)
  abs(1)
  factorial(100)

}