object lesson {
  // For comprehensions

  for (i <- 1 to 10) println(i)
  for (ch <- "Hello") println(ch)

  for (i <- 1 to 3; j <- 1 to 3 if i != j) yield (i, j)

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