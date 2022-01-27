package com.example.atinterfaces

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.atinterfaces.models.Pedido
import com.example.atinterfaces.viewmodels.PedidoViewModel
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_dados_envio.*

class DadosEnvioActivity : AppCompatActivity() {

    lateinit var pedido : Pedido

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dados_envio)

        var pedidoTexto = intent.getStringExtra("pedido")
        var pedidoObj = Gson().fromJson<Pedido>(pedidoTexto,Pedido::class.java)
        this.pedido = pedidoObj

        voltaMenu.setOnClickListener {
            var i = Intent(this,MenuActivity::class.java)
            startActivity(i)
        }

        confirmaDados.setOnClickListener {
            var nomeCliente = nome
            var enderecoCliente = endereco
            var emailCliente = email
            var rating = pedido.rating

            if(nomeCliente.text.isNullOrBlank() || enderecoCliente.text.isNullOrBlank()
                || emailCliente.text.isNullOrBlank())
            {
                Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show()
            }
            else
            {
                // Envia e-mail
                var itensPedido = pedido.produtos
                var listaProd = ""

                for(item in itensPedido)
                {
                    listaProd += item.nomeProduto + " (x" + item.qtd.toString() + ")\n"
                }

                var ratingMensagem = ""
                if(rating > 0.0f)
                {
                    ratingMensagem = "Sua avaliação para o restaurante: " + String.format("%.1f",rating) + " estrelas\n\n"
                }

                var valorPed = "Valor do pedido: R$" + String.format("%.2f",pedido.total) + "\n"
                var mensagem = nomeCliente.text.toString() + ", obrigado por realizar um pedido!\n\n" +
                        "Estaremos enviando para " + enderecoCliente.text.toString() +".\n\n" + "Pedido:\n\n" +
                        listaProd + "\n" + valorPed + "\nO prazo para chegada é de 40 minutos.\n" +
                        "O pagamento poderá ser realizado com cartão de débito ou crédito," +
                                " ticket refeição ou dinheiro.\n\n" + ratingMensagem + "Esperamos que goste!"

                var assunto = "Lucas Restaurant: Pedido Realizado"
                var para = arrayOf(emailCliente.text.toString(),"lucas.pmartins@al.infnet.edu.br")

                geraEmail(para,assunto,mensagem)

            }
        }
    }

    fun geraEmail(para: Array<String>, assunto: String, mensagem: String) {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:") // only email apps should handle this
            putExtra(Intent.EXTRA_EMAIL, para)
            putExtra(Intent.EXTRA_SUBJECT, assunto)
            putExtra(Intent.EXTRA_TEXT, mensagem)
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

}
