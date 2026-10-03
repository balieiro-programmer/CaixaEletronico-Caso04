fun limparUsuarios(emails: List<String?>) {

    var contasInvalidas = 0

    for (email in emails) {

        val tamanho = email?.length ?: 0

        if (email == null || tamanho == 0) {
            contasInvalidas++

            println("Conta inválida: e-mail ausente ou em branco. Deletando...")
        } else {
            println("Conta válida: $email")
        }
    }

    println("Contas que precisam ser apagadas: $contasInvalidas")
}

fun main() {

    val emails = listOf(
        "usuario@gmail.com",
        null,
        "",
        "teste@hotmail.com",
        null,
        "contato@gmail.com"
    )

    limparUsuarios(emails)
}