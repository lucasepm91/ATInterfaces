package com.example.atinterfaces.ui.about

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.example.atinterfaces.R
import com.example.atinterfaces.viewmodels.PedidoViewModel

/**
 * A simple [Fragment] subclass.
 */
class AboutFragment : Fragment() {

    private lateinit var pedidoViewModel: PedidoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let { act ->
            pedidoViewModel = ViewModelProviders.of(act)
                .get(PedidoViewModel::class.java)
        }
        var avaliacao = activity!!.findViewById<RatingBar>(R.id.ratingBar)

        avaliacao.rating = pedidoViewModel.rating

        avaliacao.setOnRatingBarChangeListener { ratingBar, rating, fromUser -> pedidoViewModel.rating = rating }

        var totCompra = activity!!.findViewById<TextView>(R.id.txtTot)
        subscribe(totCompra)
    }

    private fun subscribe(totCompra : TextView){
        pedidoViewModel.total!!.observe(this, Observer {

            totCompra.text = "Total: R$" + String.format("%.2f",pedidoViewModel.total.value)
        })
    }

}
