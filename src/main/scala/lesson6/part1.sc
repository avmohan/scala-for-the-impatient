object part1 {

  // Get timezones, without the continents
  val list1 = java.util.TimeZone.getAvailableIDs
    .map(_.split('/'))
    .filter(_.length > 1)
    .map(_ (1))
    // We just want to sample every 10th element
    .grouped(10)
    .map(_ (0))
    .toArray

  //Implemented slightly differently
  val list2 = java.util.TimeZone.getAvailableIDs
    .map(_.split('/'))
    .filter(_.length > 1)
    .map(_ (1))
    // We just want to sample every 10th element
    .zipWithIndex
    .filter(_._2 % 10 == 0)
    .map(_._1)

  list1 sameElements list2
}