package com.example.atinterfaces.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.atinterfaces.DadosEnvioActivity
import com.example.atinterfaces.R
import com.example.atinterfaces.viewmodels.PedidoViewModel
import kotlinx.android.synthetic.main.fragment_cart.*
import java.io.Serializable

class HomeFragment : Fragment() {

    private lateinit var pedidoViewModel: PedidoViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let { act ->
            pedidoViewModel = ViewModelProviders.of(act)
                .get(PedidoViewModel::class.java)
        }
        var totCompra = activity!!.findViewById<TextView>(R.id.txtTot)
        subscribe(totCompra)

    }

    private fun subscribe(totCompra : TextView){
        pedidoViewModel.total!!.observe(this, Observer {

            totCompra.text = "Total: R$" + String.format("%.2f",pedidoViewModel.total.value)
        })
    }
}
