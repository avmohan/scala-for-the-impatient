object part1 {
  def swap(a: Int, b: Int) = (b, a)

  def swap1(p: (Int, Int)) = p match {
    case (x, y) => (y, x)
  }

  swap(1, 2)
  swap1(1, 2)
  swap1((1, 2))

  def swapFirstTwoElementsOfArr(arr: Array[Int]) = {
    if (arr.length < 2) arr
    else {
      val Array(first, second, rest@_*) = arr
      Array(second, first) ++ rest
    }
  }

  assert(swapFirstTwoElementsOfArr(Array(1, 2, 3, 4, 5)).corresponds(Array(2, 1, 3, 4, 5))(_ == _))
  assert(swapFirstTwoElementsOfArr(Array(1, 2)).corresponds(Array(2, 1))(_ == _))
  assert(swapFirstTwoElementsOfArr(Array(1)).corresponds(Array(1))(_ == _))
}