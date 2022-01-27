package com.example.atinterfaces.models

class Produto{
    var nomeProduto : String
    var descricao : String
    var preco : Double
    var imagem : Int
    var qtd : Int

    constructor(nomeProduto : String,descricao : String, preco: Double, imagem : Int){
        this.nomeProduto = nomeProduto
        this.descricao = descricao
        this.preco = preco
        this.imagem = imagem
        this.qtd = 0
    }
}