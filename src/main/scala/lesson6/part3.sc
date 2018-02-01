object part3 {

  // Implementing a control abstraction (while loop) using recursion

  // cond & body are by name parameters. Their body is replaced
  // as is from the function call to the function body
  @scala.annotation.tailrec
  def While(cond: => Boolean)(body: => Unit): Unit = {
    if (cond) {
      body
      While(cond)(body)
    }
  }

  var i = 1
  While(i < 10) {
    println("Hello")
    i += 1
  }

  // So it was possible to implement the while loop as a library function
  // in scala, using recursion only instead of providing it as a primitive
  // But it's provided as a primitive for efficiency

}