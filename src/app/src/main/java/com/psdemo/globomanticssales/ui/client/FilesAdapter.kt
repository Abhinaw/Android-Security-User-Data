package com.psdemo.globomanticssales.ui.client

import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.psdemo.globomanticssales.R
import java.io.File
import java.util.*
import kotlin.collections.ArrayList

class FilesAdapter(private val onClickListener: OnClickListener) :
    RecyclerView.Adapter<FilesAdapter.FileHolder>() {
    private var allFiles: List<File> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FileHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.file_item, parent, false)
        return FileHolder(itemView)
    }

    override fun getItemCount(): Int {
        return allFiles.size
    }

    fun setFiles(files: List<File>) {
        this.allFiles = files
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: FileHolder, position: Int) {
        holder.bind(allFiles[position], onClickListener)
    }

    inner class FileHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.name)
        val date: TextView = itemView.findViewById(R.id.date)
        private val card: CardView = itemView.findViewById(R.id.card)

        fun bind(file: File, clickListener: OnClickListener) {
            card.setOnClickListener { clickListener.onClick(file) }

            name.text = file.name
            val calendar = Calendar.getInstance()
            val dateFormat = DateFormat.getDateFormat(itemView.context)
            calendar.timeInMillis = file.lastModified()
            date.text = dateFormat.format(calendar.time)
        }
    }

    interface OnClickListener {
        fun onClick(file: File)
    }
}