fun main() {

    var caixa = 100.0

    val estoque = mutableMapOf<String, Int>()
    val precos = mutableMapOf<String, Double>()

    var opcao: Int

    do {
        println("\n=== MENU ===")
        println("1 - Compra")
        println("2 - Venda")
        println("3 - Estoque")
        println("4 - Financeiro")
        println("0 - Sair")
        print("Escolha: ")
        opcao = readln().toInt()

        when (opcao) {

            1 -> { // COMPRA
                print("Nome do produto: ")
                val nome = readln()

                print("Preço do produto: ")
                val preco = readln().toDouble()

                if (caixa >= preco) {

                    caixa = caixa - preco 

                    precos[nome] = preco

                    val quantidadeAtual = estoque.getOrDefault(nome, 0) 
                    //Se o produto existir → pega a quantidade real
                    //Se não existir → considera que tem 0
                    estoque[nome] = quantidadeAtual + 1

                    println("Compra realizada com sucesso!")

                } else {
                    println("Saldo insuficiente!")
                }
            }

            2 -> { // VENDA
                print("Nome do produto para vender: ")
                val nome = readln()

                val quantidadeAtual = estoque.getOrDefault(nome, 0)

                if (quantidadeAtual > 0) {

                    print("Quantidade para vender: ")
                    val quantidade = readln().toInt()

                    if (quantidade <= quantidadeAtual) {

                        val precoOriginal = precos[nome] ?: 0.0
                        val precoVenda = precoOriginal * 1.05
                        val valorTotal = precoVenda * quantidade

                        caixa = caixa + valorTotal

                        estoque[nome] = quantidadeAtual - quantidade

                        println("Venda realizada com sucesso!")

                    } else {
                        println("Estoque insuficiente!")
                    }

                } else {
                    println("Produto não encontrado ou sem estoque.")
                }
            }

            3 -> { // ESTOQUE
                println("\n=== ESTOQUE ===")

                if (estoque.isEmpty()) {
                    println("Estoque vazio.")
                } else {
                    for ((produto, quantidade) in estoque) {
                        println("$produto - Quantidade: $quantidade")
                    }
                }
            }

            4 -> { // FINANCEIRO
                println("\nSaldo em caixa: R$ %.2f".format(caixa))
            }

            0 -> println("Encerrando sistema...")

            else -> println("Opção inválida!")
        }

    } while (opcao != 0)
}
