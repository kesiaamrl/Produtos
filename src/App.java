import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

import javax.print.attribute.standard.Media;

public class App {
    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        List<Produto> listaProduto = new ArrayList<>();
        List<Vendas> listaVendas = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
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
            System.out.println("Vendas por período: ");
            System.out.print("Digite uma data (dd/MM/yyyy): ");
            String dataFiltro = sc.next();
    
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            try {
                Date data = format.parse(dataFiltro);
                System.out.println("Data digitada: " + format.format(data));
            } catch (ParseException e) {
                System.out.println("Data inválida. Certifique-se de usar o formato dd/MM/yyyy.");
            }
            List <Vendas> novaListaVendas = listaVendas.stream()
            .filter(v -> v.getData().equals(dataFiltro)).collect(Collectors.toList());;
            if(novaListaVendas.isEmpty()){
                System.out.println("Venda não encontrada!");
            }
            else{
                for (Vendas vendas : novaListaVendas) {
                    System.out.println(vendas);
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
                for (Produto produto4 : listaProduto) {
            System.out.println("Digite a quantidade do produto: ");
            int quantidadeVendida = sc.nextInt();
            int quantidadeEstoque=produto4.getQuantidade()- quantidadeVendida;
            produto4.setQuantidade(quantidadeEstoque);
            if (quantidadeEstoque < 0){
                System.out.println("Venda não realizada!. Restam apenas " + quantidadeEstoque + " em estoque.");
            }else{          
            LocalDate data = LocalDate.now();
            Vendas vendas = new Vendas(data, produtoVendido, quantidadeVendida);
            listaVendas.add(vendas);
            System.out.println("Venda realizada com sucesso! ");
            System.out.println(vendas);
            }
        }
        }
        }
        } while (opcao!=0);

        
    }
}
