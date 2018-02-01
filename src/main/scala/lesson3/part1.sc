import scala.collection.mutable.ArrayBuffer

object part1 {

  // Given array buffer with positive & negative integers. Remove all but the
  // first negative number.
  def interestingNumbers(buf: Traversable[Int]) = {
    val (pos, neg) = buf.partition(x => x >= 0)
    neg.take(1) ++ pos
  }

  interestingNumbers(ArrayBuffer(1, 2, -3, 4, -5, 6, -7, 8))
  interestingNumbers(ArrayBuffer(1, 2, 3, 4, 5, 6, 7, 8))
  interestingNumbers(ArrayBuffer())
  interestingNumbers(ArrayBuffer(-1, -2))

}