

object part1 {


  // Mutable field _minutes is made private to disallow clients from
  // directly mutating it
  class Time(val hours: Int, private var _minutes: Int = 0) {
    require(0 to 23 contains hours, "")
    require(0 to 59 contains _minutes)

    // Accessor for _minutes
    def minutes = _minutes

    // Mutator for _minutes that checks the constraints
    def minutes_=(newValue: Int) {
      require(0 to 59 contains newValue)
      _minutes = newValue
    }

    def before(other: Time): Boolean =
      hours < other.hours || hours == other.hours && _minutes < other._minutes


    override def toString: String = f"$hours%02d:$minutes%02d"
  }

  object Time {
    def midnight = new Time(0, 0)

    // This should ideally be a def, not a val. See notes below
    val noon = new Time(12, 0)

  }

  new Time(0, 0)

  // below throws IAE
  //  new Time(24, 0)
  //  new Time(-1, 0)
  //  new Time(0, -1)
  //  new Time(0, 60)

  new Time(0, 13) before new Time(0, 14)
  new Time(0, 13) before new Time(1, 12)
  new Time(0, 13) before new Time(0, 13)
  new Time(1, 13) before new Time(0, 13)

  val start = new Time(13)
  //  start.minutes = 500
  start


  // midnight is defined as a `def`
  // So each time midnight is accessed, it's a new instance
  // So even though clients can mutate the instance they have, they
  // can't change the value of midnight
  val a = Time.midnight
  a
  a.minutes = 13
  a
  Time.midnight

  // noon is defined as a `val`
  // So each time noon is accessed, it's the same instance
  // So clients can change the value of noon for everyone
  val b = Time.noon
  b
  b.minutes = 13
  b
  Time.noon

  classOf[Time] == a.getClass
}