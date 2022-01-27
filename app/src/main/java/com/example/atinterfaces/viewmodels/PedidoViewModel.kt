package com.example.atinterfaces.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.atinterfaces.models.Produto
import java.io.Serializable

class PedidoViewModel : ViewModel(), Serializable{
    var produtos = mutableListOf<Produto>()
    var total = MutableLiveData<Double>().apply{value = 0.0}
    var rating : Float = 0.0f

    fun addProduto(produto : Produto){

        var indice = existeNaLista(produto)

        if(indice > -1)
            produtos[indice].qtd++
        else
        {
            produto.qtd = 1
            produtos.add(produto)
        }

        total.value = total.value?.plus(produto!!.preco)
        var aux1 = total.value
        var aux2 = 2
    }

    fun removeProduto(produto : Produto){

        var indice = existeNaLista(produto)

        if(indice > -1)
        {
            var qtd = produtos[indice].qtd
            if(qtd > 1)
                produtos[indice].qtd--
            else
                produtos.removeAt(indice)

            total.value = total.value?.minus(produto.preco)
        }

    }

    fun existeNaLista(produto : Produto) : Int{

        var cont = 0

        if(produtos.size > 0) {
            while (cont < produtos.size) {
                if ((produtos[cont].nomeProduto == produto.nomeProduto) &&
                    (produtos[cont].descricao == produto.descricao) &&
                    (produtos[cont].preco.compareTo(produto.preco) == 0)
                ) {
                    return cont
                }
                cont++
            }
        }
        return -1
    }
}