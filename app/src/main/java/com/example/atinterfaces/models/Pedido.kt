package com.example.atinterfaces.models

import java.io.Serializable

class Pedido(produtos : MutableList<Produto>,total : Double, rating: Float) : Serializable{
    val produtos = produtos
    val total = total
    val rating = rating
}