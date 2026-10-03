fun processarTransacoes() {

    val transacoes: List<Double?> = listOf(
        50.0,
        null,
        120.5,
        null,
        10.0
    )

    var total = 0.0

    for (valor in transacoes) {

        if (valor != null) {
            total += valor
        } else {
            println("Transação ignorada")
        }
    }

    println("Valor total processado: R$ $total")
}

fun main() {
    processarTransacoes()
}