package com.example.atinterfaces.ui.cardapio

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.get
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.atinterfaces.R
import com.example.atinterfaces.adapters.ProdutoAdapter
import com.example.atinterfaces.models.Produto
import com.example.atinterfaces.viewmodels.PedidoViewModel
import kotlinx.android.synthetic.main.fragment_cardapio.*
import kotlinx.android.synthetic.main.fragment_item_produtos.*

/**
 * A simple [Fragment] subclass.
 */
class CardapioFragment : Fragment() {

    private lateinit var pedidoViewModel: PedidoViewModel

    var listaProdutos = mutableListOf<Produto>(Produto("Pizza","Calabresa com cebola e orégano",37.90,R.drawable.pizza),
        Produto("Hambúrguer","Carne, tomate, alface e queijo",12.50,R.drawable.hamburguer),
        Produto("Costela","Costela bovina",42.50,R.drawable.costela),
        Produto("Cerveja","Cerveja artesanal 500mL",17.50,R.drawable.cervejasembkg),
        Produto("Refrigerante","Coca Cola 2L",6.00,R.drawable.refrigerante))

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cardapio, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let { act ->
            pedidoViewModel = ViewModelProviders.of(act)
                .get(PedidoViewModel::class.java)
        }
        var totCompra = activity!!.findViewById<TextView>(R.id.txtTot)

        configurarRecyclerView()
        subscribe(totCompra)
    }

    private fun configurarRecyclerView(){
        listagemProdutos.layoutManager = LinearLayoutManager(activity)
        listagemProdutos.adapter = ProdutoAdapter(pedidoViewModel,listaAtualizada())
    }

    private fun subscribe(totCompra : TextView){
        pedidoViewModel.total!!.observe(this, Observer {

            totCompra.text = "Total: R$" + String.format("%.2f",pedidoViewModel.total.value)
        })
    }

    private fun listaAtualizada() : MutableList<Produto>
    {
        var listaAtlz = listaProdutos.toMutableList()

        if(pedidoViewModel.produtos.size > 0)
        {
            var contPedProd = 0

            while(contPedProd < pedidoViewModel.produtos.size)
            {
                var contAdpProd = 0
                while(contAdpProd < listaAtlz.size)
                {
                    if(listaAtlz[contAdpProd].nomeProduto == pedidoViewModel.produtos[contPedProd].nomeProduto)
                        listaAtlz[contAdpProd].qtd = pedidoViewModel.produtos[contPedProd].qtd
                    contAdpProd++
                }
                contPedProd++
            }
        }
        return listaAtlz
    }
}
