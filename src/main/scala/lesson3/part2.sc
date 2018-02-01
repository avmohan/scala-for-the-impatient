import scala.collection.mutable

object part2 {

  def getWordCounts1(text: String) = {
    val counts = mutable.Map.empty[String, Int]
    for (word <- text.split("\\s+")) {
      counts(word) = counts.getOrElse(word, 0) + 1
    }
    counts
  }

  def getWordCounts2(text: String) = {
    var counts = Map.empty[String, Int]
    for (word <- text.split("\\s+")) {
      counts += (word -> (counts.getOrElse(word, 0) + 1))
    }
    counts
  }

  def getWordCounts3(text: String) = {
    text.split("\\s+")
      .foldLeft(Map.empty[String, Int]) {
        (counts, word) => counts + (word -> (counts.getOrElse(word, 0) + 1))
  }
  }

  val text = "hello this is a sample word count example there are multiple instances" +
    " of hello here so it should have a higher count"
  val counts1 = getWordCounts1(text)
  val counts2 = getWordCounts2(text)
  val counts3 = getWordCounts3(text)

  counts2 == counts3
  counts2 eq counts3

  counts1 == counts2
  counts1 eq counts2

  counts1 == counts3
  counts1 eq counts3

}