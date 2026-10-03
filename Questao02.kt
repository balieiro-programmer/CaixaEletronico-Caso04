fun verificarEntregas(enderecos: List<String?>) {

    for (endereco in enderecos) {

        val enderecoFinal = endereco ?: "Endereço Desconhecido"

        if (enderecoFinal == "Endereço Desconhecido") {
            println("Entrega Pendente: Falta de dados")
        } else {
            println("Rota traçada para: $enderecoFinal")
        }
    }
}

fun main() {
    val enderecos = listOf(
        "Rua das Flores, 100",
        null,
        "Avenida Brasil, 500",
        null
    )

    verificarEntregas(enderecos)
}