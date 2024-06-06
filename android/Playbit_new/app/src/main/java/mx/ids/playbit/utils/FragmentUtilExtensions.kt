package mx.ids.playbit.utils

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.icu.util.Calendar
import android.net.Uri
import android.provider.MediaStore
import android.util.Base64
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import mx.ids.playbit.ui.alert.AlertActivity
import java.io.ByteArrayOutputStream
import java.time.LocalDate
import java.time.LocalTime
import java.util.zip.GZIPInputStream
import java.util.zip.GZIPOutputStream

// Extension function to register and launch the gallery picker
fun Fragment.registerGalleryPicker(onImageSelected: (Uri) -> Unit) =
    registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let(onImageSelected)
    }

// Extension function to handle image Uri
fun Fragment.handleImageUri(
    uri: Uri,
    onImageProcessed: (String) -> Unit
) {
    val bitmap = MediaStore.Images.Media.getBitmap(requireActivity().contentResolver, uri)
    val imageString = bitmapToString(bitmap)
    onImageProcessed(imageString)
}

//extension function to compress image
fun compressImageString(imageString: String): String {
    val byteArrayOutputStream = ByteArrayOutputStream()
    val gzipOutputStream = GZIPOutputStream(byteArrayOutputStream)
    gzipOutputStream.write(imageString.toByteArray())
    gzipOutputStream.close()
    return Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT)
}

//extension function to decompress image
fun decompressImageString(compressedImageString: String): String {
    val compressedBytes = Base64.decode(compressedImageString, Base64.DEFAULT)
    val byteArrayInputStream = compressedBytes.inputStream()
    val gzipInputStream = GZIPInputStream(byteArrayInputStream)
    return gzipInputStream.bufferedReader().use { it.readText() }
}

// Function to convert Bitmap to String
fun bitmapToString(bitmap: Bitmap): String {
    val byteArrayOutputStream = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
    val byteArray = byteArrayOutputStream.toByteArray()
    return Base64.encodeToString(byteArray, Base64.DEFAULT)
}

// Function to convert Base64 string to Bitmap
fun base64ToBitmap(base64Str: String): Bitmap? {
    val decodedBytes = Base64.decode(base64Str, Base64.DEFAULT)
    return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
}

// Extension function to show loader and overlay
fun Fragment.showLoader(loaderView: View, overlayView: View) {
    loaderView.visibility = View.VISIBLE
    overlayView.visibility = View.VISIBLE
    overlayView.isClickable = true
    overlayView.isFocusable = true
    overlayView.isFocusableInTouchMode = true
}

// Extension function to hide loader and overlay
fun Fragment.hideLoader(loaderView: View, overlayView: View) {
    loaderView.visibility = View.GONE
    overlayView.visibility = View.GONE
    overlayView.isClickable = false
    overlayView.isFocusable = false
    overlayView.isFocusableInTouchMode = false
}

fun Fragment.showDatePickerDialog(onDateSet: (String) -> Unit) {
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    DatePickerDialog(requireContext(), { _, selectedYear, selectedMonth, selectedDay ->
        val localDate = LocalDate.of(selectedYear, selectedMonth + 1, selectedDay)
        val formattedDate = localDate.formatToString()
        onDateSet(formattedDate)
    }, year, month, day).show()
}

fun Fragment.showDatePickerDialogInLocal(onDateSet: (LocalDate) -> Unit) {
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    DatePickerDialog(requireContext(), { _, selectedYear, selectedMonth, selectedDay ->
        val localDate = LocalDate.of(selectedYear, selectedMonth + 1, selectedDay)
        val formattedDate = localDate.formatToString()
        onDateSet(localDate)
    }, year, month, day).show()
}

fun Fragment.showTimePickerDialog(onTimeSet: (String) -> Unit) {
    val calendar = Calendar.getInstance()
    val hour = calendar.get(Calendar.HOUR_OF_DAY)
    val minute = calendar.get(Calendar.MINUTE)

    TimePickerDialog(requireContext(), { _, selectedHour, selectedMinute ->
        val localTime = LocalTime.of(selectedHour, selectedMinute)
        val formattedTime = localTime.formatToString()
        onTimeSet(formattedTime)
    }, hour, minute, true).show()
}

fun Fragment.showTimePickerDialogInLocal(onTimeSet: (LocalTime) -> Unit) {
    val calendar = Calendar.getInstance()
    val hour = calendar.get(Calendar.HOUR_OF_DAY)
    val minute = calendar.get(Calendar.MINUTE)

    TimePickerDialog(requireContext(), { _, selectedHour, selectedMinute ->
        val localTime = LocalTime.of(selectedHour, selectedMinute,0)
        val formattedTime = localTime.formatToString()
        onTimeSet(localTime)
    }, hour, minute, true).show()
}

//extension function to show the generic alert
fun Fragment.showAlert(screenType : String, message : String) {
    val intent = Intent(requireContext(), AlertActivity::class.java).apply {
        putExtra(EXTRA_SCREEN_TYPE, screenType)
        putExtra(EXTRA_MESSAGE, message)
    }
    startActivity(intent)
}