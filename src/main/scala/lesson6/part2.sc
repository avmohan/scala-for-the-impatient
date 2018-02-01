

object part2 {
  def factorial(n: Int): Int =
    1 to n reduceLeft (_ * _)

  assert(factorial(10) == 3628800)

  def pow2(n: Int): Int =
    1 to n map (_ => 2) reduceLeft (_ * _)

  def pow2Simpler(n: Int): Int =
    Iterator.fill(n)(2).product

  assert(pow2(10) == 1024)
  assert(pow2Simpler(10) == 1024)

  def concat(strings: Seq[String], separator: String): String =
    strings.reduceLeft(_ + separator + _)

  concat(Array("Mary", "had", "a", "little", "lamb"), " ")


}