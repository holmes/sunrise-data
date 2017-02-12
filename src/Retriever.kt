import java.io.BufferedReader
import java.io.FileOutputStream
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.URL
import java.time.LocalDate

fun main(args: Array<String>) {
  val start = LocalDate.of(2031, 1, 1)
  val end = LocalDate.of(2051, 1, 1)

  val lat = "37.8857582"
  val lon = "-122.1180201"

  var currentDate = start

  while (currentDate < end) {
    val year = currentDate.year
    val month = currentDate.monthValue
    val day = currentDate.dayOfMonth

    val targetURL = "http://api.sunrise-sunset.org/json?lat=$lat&lng=$lon&date=$year-$month-$day&formatted=0"
    val file = OutputStreamWriter(FileOutputStream("data/$year-$month-$day.json"))

    val connection = URL(targetURL).openConnection()
    val reader = BufferedReader(InputStreamReader(connection.getInputStream()))

    // It's 1 line. I don't care.
    file.write(reader.readLine())
    reader.close()
    file.close()

    currentDate = currentDate.plusDays(1)
    Thread.sleep(1000)
  }
}
