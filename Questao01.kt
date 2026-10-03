fun calcularDesconto(valor: Double, cupom: String?): Double {
    return when (cupom) {
        "PROMO10" -> valor - 10
        "PROMO20" -> valor - 20
        else -> valor
    }
}

fun main() {
    println(calcularDesconto(100.0, "PROMO10"))
    println(calcularDesconto(100.0, "PROMO20"))
    println(calcularDesconto(100.0, null))
}