object part1 {

  // Default arguments
  def isVowel(ch: Char, ignoreCase: Boolean = true, vowelChars: String = "aeiou") = {
    if (ignoreCase)
      vowelChars.toLowerCase().contains(ch.toLower)
    else
      vowelChars.contains(ch)
  }

  isVowel('h')
  isVowel('a')
  isVowel('o')

  def vowels1(str: String) = {
    var vows: StringBuilder = new StringBuilder("")
    for (ch <- str) {
      if (isVowel(ch)) vows += ch
    }
    vows.toString()
  }

  def vowels2(str: String, ignoreCase: Boolean = true, vowelChars: String = "aeiou") = {
    for (ch <- str if isVowel(ch, ignoreCase, vowelChars)) yield ch
  }

  def vowels3(str: String): String = {
    if (str.isEmpty) ""
    else if (isVowel(str.head)) str.head + vowels3(str.tail)
    else vowels3(str.tail)
  }

  def vowels4(str: String) = {
    var i = 0
    val vows: StringBuilder = new StringBuilder("")
    while (i < str.length) {
      if (isVowel(str(i))) vows += str(i)
      i += 1
    }
    vows.toString()
  }

  def vowels5(str: String, ignoreCase: Boolean = true, vowelChars: String = "aeiou") = {
    str.filter(isVowel(_, ignoreCase, vowelChars))
  }

  vowels1(str = "hello") // named argument str
  vowels2("hEllo")
  vowels2("hEllo")
  vowels3("hello")
  vowels4("hello")

  vowels2("hEllo")
  vowels2("hEllo", ignoreCase = false)
  vowels5("hEllo")
  vowels5("hEllo", ignoreCase = false)

  // Varargs
  def sum(xs: Int*) = xs.sum

  sum(1, 2, 4)
  val q = Array(1, 2, 4)

  // Calling a varargs function with an existing sequence
  sum(q: _*)
}