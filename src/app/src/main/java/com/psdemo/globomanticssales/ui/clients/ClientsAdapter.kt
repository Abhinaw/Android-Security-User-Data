package com.psdemo.globomanticssales.ui.clients

import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.psdemo.globomanticssales.R
import com.psdemo.globomanticssales.data.Client
import java.util.*
import kotlin.collections.ArrayList

class ClientsAdapter(private val onClickListener: OnClickListener) :
    RecyclerView.Adapter<ClientsAdapter.ClientHolder>() {
    private var allClients: List<Client> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.client_item, parent, false)
        return ClientHolder(itemView)
    }

    override fun getItemCount(): Int {
        return allClients.size
    }

    fun setClients(clients: List<Client>) {
        this.allClients = clients
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ClientHolder, position: Int) {
        holder.bind(allClients[position], onClickListener)
    }

    inner class ClientHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.name)
        val date: TextView = itemView.findViewById(R.id.date)
        private val card: CardView = itemView.findViewById(R.id.card)

        fun bind(client: Client, clickListener: OnClickListener) {
            card.setOnClickListener { clickListener.onClick(client.id) }

            name.text = client.name

            val calendar = Calendar.getInstance()
            val dateFormat = DateFormat.getDateFormat(itemView.context)
            calendar.timeInMillis = client.date
            date.text = dateFormat.format(calendar.time)
        }
    }

    interface OnClickListener {
        fun onClick(id: Int)
    }
}