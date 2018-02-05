object part2 {

  abstract class Item

  case class Article(description: String, price: Double) extends Item

  case class Bundle(description: String, discount: Double, items: Item*) extends Item

  val book = Article("Scala for the Impatient", 39.95)
  val bottle = Article("Old Potrero Straight Rye Whiskey", 79.95)

  val gift = Bundle(description = "Book and Bottle", 10, book, bottle)


  def price(it: Item): Double = it match {
    case Article(_, p) => p
    case Bundle(_, discount, items@_ *) => items.map(price).sum - discount
  }

  price(book)
  price(bottle)
  price(gift)

  val special = Bundle("Father's day special", 20.0,
    book,
    Bundle("Anchor Distillery Sampler", 10.0,
      bottle,
      Article("Junipero Gin", 39.95)))
  price(special)

  val Bundle(_, _, Article(descr, price), items@_) = special


}