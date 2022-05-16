package com.psdemo.globomanticssales

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Paint
import android.graphics.pdf.PdfDocument
import android.graphics.pdf.PdfDocument.PageInfo
import android.text.format.DateFormat
import android.util.Log
import com.psdemo.globomanticssales.data.Client
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.*

private const val FILENAME = "proposal.pdf"

fun Context.proposalExists(id: Int) =
    File(this.getFolder(id), FILENAME).exists()

fun Context.buildPdf(client: Client) {
    val directory = this.getFolder(client.id)
    val inch = 72
    val halfInch = inch / 2
    val inchFloat = inch.toFloat()
    val halfInchFloat = halfInch.toFloat()
    val document = PdfDocument()
    val pageInfo = PageInfo.Builder(inch * 8 + halfInch, inch * 11, 1).create()
    val page = document.startPage(pageInfo)
    val canvas = page.canvas

    val paint = Paint()
    paint.color = Color.BLACK
    paint.textAlign = Paint.Align.RIGHT

    val calendar = Calendar.getInstance()
    val dateFormat = DateFormat.getDateFormat(this)
    calendar.timeInMillis = client.date
    canvas.drawText(
        dateFormat.format(calendar.time),
        inchFloat * 7 + halfInchFloat,
        inchFloat,
        paint
    )

    val logo = this.resources.getDrawable(R.drawable.logo, theme)
    logo.setBounds(inch, inch, inch * 6 + halfInch, inch * 2)
    logo.draw(canvas)

    val sharedPref = this.getSharedPreferences(
        getString(R.string.profile_preference_key),
        Context.MODE_PRIVATE
    )
    val salesPersonName = sharedPref.getString(
        getString(R.string.profile_name_key),
        getString(R.string.profile_name_default)
    )

    paint.textAlign = Paint.Align.LEFT

    canvas.drawText(
        "Salesperson: $salesPersonName",
        inchFloat,
        inchFloat * 2 + halfInchFloat,
        paint
    )
    canvas.drawText(
        "Client: ${client.name}",
        inchFloat,
        inchFloat * 3,
        paint
    )
    canvas.drawText(
        "Order: ${client.order}",
        inchFloat,
        inchFloat * 3 + halfInchFloat,
        paint
    )
    canvas.drawText(
        "Terms: ${client.terms}",
        inchFloat,
        inchFloat * 4,
        paint
    )

    document.finishPage(page)

    val file = File(directory, FILENAME)
    try {
        document.writeTo(FileOutputStream(file))
    } catch (e: IOException) {
        Log.e("FileUtils", "Proposal write error", e)
    }

    document.close()
}

fun Context.getFiles(id: Int) = this.getFolder(id).listFiles()!!.asList()

fun Context.saveImage(bitmap: Bitmap, fileName: String, id: Int) {
    val directory = this.getFolder(id)
    val file = File(directory, "$fileName.png")
    val outputStream = FileOutputStream(file)
    bitmap.compress(Bitmap.CompressFormat.PNG, 90, outputStream)
    outputStream.flush()
    outputStream.close()
}

private fun Context.getFolder(id: Int): File {
    val directory = File("${this.filesDir}/$id")
    if (!directory.exists()) {
        directory.mkdir()
    }

    return directory
}