import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


public class App {
    public static void main(String[] args) throws InterruptedException, IOException {
        List<Produto> listaProduto = new ArrayList<>();
        List<Vendas> listaVendas = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter formato=DateTimeFormatter.ofPattern("dd/MM/yyyy");
        int opcao;

        do {
            System.out.println("1 - Incluir produto");
            System.out.println("2 - Consultar produto");
            System.out.println("3 - Listagem de produtos");
            System.out.println("4 - Vendas por período – detalhado ");
            System.out.println("5 - Realizar venda");
            System.out.println("0 - Sair");
            opcao=sc.nextInt();

        if(opcao==1){
            System.out.println("Digite o código do produto: ");
            String codigo=sc.next();
            System.out.println("Digite o nome do produto: " );
            String nome=sc.next();
            System.out.println("Digite o valor do produto");
            double valor=sc.nextDouble();
            System.out.println("Informe quantas unidades serão adicionadas");
            int quantidade = sc.nextInt();

            listaProduto.add(new Produto(codigo, nome, valor, quantidade));
            
        }
        else if(opcao==2){
            System.out.println("Digite o código do produto a ser consultado: ");
            String codigo=sc.next();

            List <Produto> novaLista = listaProduto.stream()
            .filter(p -> p .getCodigo().contains(codigo)).collect(Collectors.toList());;
            if(novaLista.isEmpty()){
                System.out.println("Produto não encontrado!");
            }
            else{
                for (Produto produto : novaLista) {
                    System.out.println(produto);
                }
            }
        }
        else if(opcao==3){
            System.out.println("Produtos cadastrados: ");
            for (Produto produto : listaProduto) {
                System.out.println(produto);
            }

            DoubleSummaryStatistics resumo = listaProduto.stream()
            .collect(Collectors.summarizingDouble(Produto::getValor));
            System.out.println("O valor máximo dos produtos cadastrados é: " + resumo.getMax());
            System.out.println("\nO valor médio dos produtos cadastrados é: " + resumo.getAverage());
            System.out.println("\nO valor mínimo dos produtos cadastrados é: " + resumo.getMin());

        }
        else if (opcao==4){
            System.out.println("\nVendas por período: \n");
            System.out.println("Digite a data inicial: ");
            LocalDate data1=LocalDate.parse(sc.next(), formato);
            System.out.println("Digite a data final: ");
            LocalDate data2=LocalDate.parse(sc.next(), formato);
            double valorTotal=0.0;
            double media=0.0;

            for (Vendas vendas : listaVendas) {
                if(vendas.getData().compareTo(data1) >=0 && vendas.getData().compareTo(data2) < 1){
                    valorTotal+=vendas.getProduto().getValor() * vendas.getQuantidadeVendida();
                System.out.println(vendas);
                System.out.println("\nO valor total das vendas é: " + valorTotal);
                media+=valorTotal/vendas.getQuantidadeVendida();
                System.out.println("A média do valor vendido no período é: " + media);
                } else{
                    System.out.println("Não foi localizado nenhuma venda neste período!");
                }
            }

        }
                
        else if (opcao==5){
        
            System.out.println("Digite o código do produto: ");
            String produtoVendido = sc.next();
            
            List<Produto> listaP= listaProduto.stream()
            .filter(p->p.getCodigo().equals(produtoVendido)).collect(Collectors.toList());
            if (listaP.isEmpty()) {
                System.out.println("Produto não localizado!");
            } else {
                for (Produto produto : listaProduto) {
            System.out.println("Digite a quantidade do produto: ");
            int quantidadeVendida = sc.nextInt();

            int quantidadeEstoque=produto.getQuantidade()- quantidadeVendida;
            
            produto.setQuantidade(quantidadeEstoque);
            if (quantidadeEstoque < 0){
                System.out.println("Venda não realizada!. Restam apenas " + quantidadeEstoque + " em estoque.");
            }else{          
            LocalDate data = LocalDate.now();
            Vendas vendas = new Vendas(data, produtoVendido, quantidadeVendida, produto);
            listaVendas.add(vendas);
            System.out.println("Venda realizada com sucesso! ");

            }
        }
        }
        }
        } while (opcao!=0);

        
    }
}
