import scala.collection.mutable.ArrayBuffer

object part4 {

  "New York".partition(_.isUpper)

  var buf = ArrayBuffer(1, 2, -3, 4, -5, 6, -7, 8)
  val (negatives, positives) = buf.partition(_ < 0)
  buf = negatives.take(1) ++ positives

  val symbols = Array("<", "-", ">")
  val counts = Array(2, 10, 2)
  val pairs = symbols.zip(counts)
  for ((symbol, count) <- symbols.zip(counts)) print(symbol * count)
}