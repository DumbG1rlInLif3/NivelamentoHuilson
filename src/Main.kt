fun main() {

    //PILHA de numeros -> guarda valores
    val numeros = ArrayDeque<Double>()

    //PILHA de operadores -> guarda + - * /
    //Algoritmo de avaliação de expressão
    val operadores = ArrayDeque<Char>()


    println("Digite uma expressão matemática: ")
    val expressao = readln()


        //o programa bloqueia expressões muito curtas
        if (expressao.length < 9) {
            println("A expressão deve ter no mínimo 9 caracteres.")
            return

        }

        for (c in expressao) {

            if (!c.isDigit() && c !in "+-*/() ") {
                //c -> caracteres

                println("Expressão contém caracteres inválidos.")
                return
                //verifica caracteres permitidos, impedindo coisa como: 5 + 8a * 2
            }
        }

        var i = 0

        while (i < expressao.length) {

            val c = expressao[i]
            i++ //percore cada caractere da expressão

            if (c == ' ') {
                continue
            } //ignora espaços

            if (c.isDigit()) {
                var numero = c.toString()

                while (i < expressao.length && expressao[i].isDigit()) {
                    numero += expressao[i]
                    i++
                }
                numeros.addLast(numero.toDouble())
            } //se for encontrado número, é feito a leitura do número completo

            if (c == '(') {

                operadores.addLast(c)
            } //se abrir parênteses

            if (c == ')') {

                while (operadores.last() != '(') {

                    calcular(numeros, operadores)
                }

                operadores.removeLast()
            } //se fechar parênteses

            if (c in "+-*/") {

                while (
                    operadores.isNotEmpty() &&
                    operadores.last() != '(' &&
                    prioridade(operadores.last()) >= prioridade(c)
                ) {

                    calcular(numeros, operadores)

                }

                operadores.addLast(c)
            }
            //se for operador, respeita a ordem de prioridade.
            //Curiosidade: implementado o mesmo princípio usado no algoritmo chamado: "Shunting Yard Algoritm
            //Criado por Edsger Dijkstra, é utilizado para interpretar expressões matemáticas em linguagens de programação :)
        }

        //resolve operações restantes
        while (operadores.isNotEmpty()) {

            calcular(numeros, operadores)

        }

        println("Resultado: ${numeros.last()}")
    }

    //função de cálculo para resolver a operação
    fun calcular (numeros: ArrayDeque<Double>, operadores: ArrayDeque<Char>) {

        val b = numeros.removeLast()
        val a = numeros.removeLast()
        //pega os dois números
        val op = operadores.removeLast()

        val resultado = when (op) {

            '+' -> a + b
            '-' -> a - b
            '*' -> a * b
            '/' -> a / b
            else -> 0.0

        }

        numeros.addLast(resultado)
    }

    fun prioridade(op: Char): Int {

        return when (op) {
        '+', '-' -> 1
        '*', '/' -> 2
        else -> 0
    }
}




