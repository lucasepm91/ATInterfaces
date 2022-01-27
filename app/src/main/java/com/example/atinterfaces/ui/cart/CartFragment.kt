package com.example.atinterfaces.ui.cart

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders
import com.example.atinterfaces.DadosEnvioActivity

import com.example.atinterfaces.R
import com.example.atinterfaces.models.Pedido
import com.example.atinterfaces.viewmodels.PedidoViewModel
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_cart.*
import org.json.JSONObject
import java.io.Serializable

/**
 * A simple [Fragment] subclass.
 */
class CartFragment : Fragment() {

    private lateinit var pedidoViewModel: PedidoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let { act ->
            pedidoViewModel = ViewModelProviders.of(act)
                .get(PedidoViewModel::class.java)
        }

        //var btnNext = activity!!.findViewById<TextView>(R.id.btnEnvPedido)

        btnEnvPedido.setOnClickListener {
            if(pedidoViewModel.produtos.size > 0)
            {
                var pedido = Pedido(pedidoViewModel.produtos,pedidoViewModel.total.value!!,pedidoViewModel.rating)
                var textoPedido = Gson().toJson(pedido)
                val intent = Intent(activity!!, DadosEnvioActivity::class.java)
                intent.putExtra("pedido", textoPedido)
                startActivity(intent)
            }
        }
    }

}
