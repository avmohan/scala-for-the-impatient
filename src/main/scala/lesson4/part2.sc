object part2 {

  class Time(h: Int, m: Int = 0) {
    require(0 to 23 contains h)
    require(0 to 59 contains m)

    private val minutesSinceMidnight = h * 60 + m

    def <(other: Time): Boolean =
      this - other < 0

    def -(other: Time) =
      minutesSinceMidnight - other.minutesSinceMidnight

    override def toString: String = f"$h%02d:$m%02d"

    def hours() = minutesSinceMidnight / 60

    def minutes() = minutesSinceMidnight % 60

  }

  object Time {
    def apply(h: Int, m: Int = 0): Time = new Time(h, m)
  }


  Time(0, 0)

  // below throws IAE
  //  new Time(24, 0)
  //  new Time(-1, 0)
  //  new Time(0, -1)
  //  new Time(0, 60)

  Time(0, 13) < Time(0, 14)
  Time(0, 13) < Time(1, 12)
  Time(0, 13) < Time(0, 13)
  Time(1, 13) < Time(0, 13)

  Time(13).hours
  Time(13, 17).minutes

  Time(0)
}