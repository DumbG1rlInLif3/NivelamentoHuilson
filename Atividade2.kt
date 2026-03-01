fun main() {

    println("Digite primeiro número: ")
    val n1 = readln().toInt()
    println("Digite segundo número: ")
    val n2 = readln().toInt()
    println("Selecione operação: + , - , / ou *")

    val operacao = readln()

    val resultado = when (operacao) {
        "+" -> n1 + n2
        "-" -> n1 - n2
        "*" -> n1 * n2
        "/" -> {
            if (n2 != 0) n1 / n2
            else {
                println("Não é possível dividir por zero.")
                return
            }
        }
        else -> {
            println("Operação inválida.")
            return
        }
    }

    println("Resultado: $resultado")

}