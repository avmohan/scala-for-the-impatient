object part3 {
  val text = "Mary had a little lamb its fleece was white as snow and everywhere" +
    " that Mary went the lamb was sure to go"
  val words = text.split("\\s+")
  val groupsByStartingLetter = words.groupBy(_.charAt(0))
  val groupsByLength = words.groupBy(_.length)

  for ((start, words) <- groupsByStartingLetter) {
    println(start, words.mkString("[", ", ", "]"))
  }

  for ((start, words) <- groupsByLength) {
    println(start, words.mkString("[", ", ", "]"))
  }
}