package com.psdemo.globomanticssales.ui.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.psdemo.globomanticssales.MainActivity
import com.psdemo.globomanticssales.R
import com.psdemo.globomanticssales.data.Client
import kotlinx.android.synthetic.main.fragment_add.*

class AddFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val addViewModel = ViewModelProvider(this).get(AddViewModel::class.java)
        btnSave.setOnClickListener {
            addViewModel.insert(
                Client(
                    date = System.currentTimeMillis(),
                    name = txtName.text.toString(),
                    order = txtOrder.text.toString(),
                    terms = txtTerms.text.toString()
                )
            )

            (activity as MainActivity).hideKeyboard()
            val navController = Navigation.findNavController(view)
            navController.navigate(AddFragmentDirections.backToClients())
        }
    }
}
