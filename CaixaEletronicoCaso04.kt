data class Transacao(
    val tipo: String,
    val valor: Double,
    val descricao: String
)

class ContaBancaria(
    val titular: String,
    var saldo: Double,
    val limiteDiario: Double
) {
    private var totalSacadoHoje = 0.0
    val transacoes = mutableListOf<Transacao>()

    fun consultarSaldo() {
        println("\n===== SALDO =====")
        println("Titular: $titular")
        println("Saldo: R$ %.2f".format(saldo))
        println("Limite diário restante: R$ %.2f".format(limiteDiario - totalSacadoHoje))
    }

    fun sacar(valor: Double, fimDeSemana: Boolean) {

        if (valor <= 0) {
            println("Valor de saque inválido.")
            return
        }

        val taxa = if (fimDeSemana) 5.0 else 0.0
        val valorTotal = valor + taxa
        val limiteRestante = limiteDiario - totalSacadoHoje

        if (valor > limiteRestante) {

            println("Saque negado!")
            println("Limite diário restante: R$ %.2f".format(limiteRestante))

        } else if (valorTotal > saldo) {

            println("Saque negado!")
            println("Saldo insuficiente.")

        } else {

            saldo -= valorTotal
            totalSacadoHoje += valor

            transacoes.add(
                Transacao(
                    "SAQUE",
                    valor,
                    if (fimDeSemana)
                        "Saque com taxa de R$ 5,00"
                    else
                        "Saque sem taxa"
                )
            )

            println("\nSaque realizado com sucesso!")
            println("Valor sacado: R$ %.2f".format(valor))

            if (fimDeSemana) {
                println("Taxa de fim de semana: R$ 5,00")
                println("Total descontado: R$ %.2f".format(valorTotal))
            }
        }
    }

    fun depositar(valor: Double, voucherBonus: Double?) {

        if (valor <= 0) {
            println("Valor de depósito inválido.")
            return
        }

        saldo += valor

        transacoes.add(
            Transacao(
                "DEPÓSITO",
                valor,
                "Depósito realizado"
            )
        )

        println("\nDepósito realizado com sucesso!")
        println("Valor depositado: R$ %.2f".format(valor))

        // Null Safety
        voucherBonus?.let {
            saldo += it

            transacoes.add(
                Transacao(
                    "BÔNUS",
                    it,
                    "Bônus do voucher"
                )
            )

            println("Voucher aplicado!")
            println("Bônus recebido: R$ %.2f".format(it))
        }
    }

    fun mostrarTransacoes() {

        println("\n===== HISTÓRICO =====")

        if (transacoes.isEmpty()) {

            println("Nenhuma transação realizada.")

        } else {

            transacoes.forEachIndexed { indice, transacao ->

                println(
                    "${indice + 1} - ${transacao.tipo} | " +
                    "R$ %.2f | ${transacao.descricao}"
                        .format(transacao.valor)
                )
            }
        }
    }
}

fun main() {

    println("================================")
    println("       CAIXA ELETRÔNICO")
    println("================================")

    print("Digite o nome do titular: ")
    val titular = readln()

    print("Digite o saldo inicial: R$ ")
    val saldoInicial = readln().toDoubleOrNull() ?: 0.0

    val conta = ContaBancaria(
        titular = titular,
        saldo = saldoInicial,
        limiteDiario = 1000.0
    )

    print("Hoje é fim de semana? (s/n): ")
    val fimDeSemana = readln().lowercase() == "s"

    var executando = true

    // WHILE: mantém o caixa funcionando até o usuário escolher sair
    while (executando) {

        println("\n========== MENU ==========")
        println("1 - Consultar saldo")
        println("2 - Sacar")
        println("3 - Depositar")
        println("4 - Histórico")
        println("5 - Sair")
        println("==========================")

        print("Escolha uma opção: ")
        val opcao = readln().toIntOrNull()

        // WHEN: escolha das operações do caixa
        when (opcao) {

            1 -> {
                conta.consultarSaldo()
            }

            2 -> {

                print("Digite o valor do saque: R$ ")
                val valor = readln().toDoubleOrNull()

                if (valor != null) {
                    conta.sacar(valor, fimDeSemana)
                } else {
                    println("Valor inválido.")
                }
            }

            3 -> {

                print("Digite o valor do depósito: R$ ")
                val valor = readln().toDoubleOrNull()

                if (valor != null) {

                    print("Digite o valor do voucher bônus ou pressione ENTER para não usar: ")

                    val entradaVoucher = readln()

                    // Null Safety
                    val voucherBonus: Double? =
                        if (entradaVoucher.isBlank()) {
                            null
                        } else {
                            entradaVoucher.toDoubleOrNull()
                        }

                    conta.depositar(valor, voucherBonus)

                } else {
                    println("Valor inválido.")
                }
            }

            4 -> {
                conta.mostrarTransacoes()
            }

            5 -> {

                executando = false

                println("\nObrigado por utilizar o Caixa Eletrônico!")
            }

            else -> {
                println("Opção inválida.")
            }
        }
    }
}