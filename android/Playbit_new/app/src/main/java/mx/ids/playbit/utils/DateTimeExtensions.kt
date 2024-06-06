package mx.ids.playbit.utils

import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
/**
 * Extensions used to manipulate date or time to a specific format
 * @author Leonardo Aguilar Rodríguez
 *  */
fun LocalDate.formatToString(): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    return this.format(formatter)
}

fun LocalTime.formatToString(): String {
    val formatter = DateTimeFormatter.ofPattern("HH:mm:ss")
    return this.format(formatter)
}