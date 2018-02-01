import java.io.{FileInputStream, InputStream}

object part3 {

  trait Logged {
    def log(msg: String): Unit = {}
  }

  trait ConsoleLogger extends Logged {
    override def log(msg: String): Unit = {
      println(msg)
    }
  }

  trait Buffered extends InputStream with Logged {
    val SIZE = 1024
    private var end = 0
    private val buffer = new Array[Byte](SIZE)
    private var pos = 0

    override def read() = {
      if (pos == end) {
        log("Reading from file")
        end = super.read(buffer, 0, SIZE)
        pos = 0
      }
      if (pos == end) -1 else {
        log("Reading from buffer")
        pos += 1
        buffer(pos - 1)
      }
    }
  }

  val filename = "/usr/share/dict/words"
  val bufferedFs = new FileInputStream(filename) with Buffered with ConsoleLogger
  bufferedFs.read()
  bufferedFs.read()

}