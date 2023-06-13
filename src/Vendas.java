import java.io.DataInput;
import java.time.LocalDate;
import java.util.Date;
import java.time.format.DateTimeFormatter;

public class Vendas  {
    private LocalDate data;
    private String produtoVendido;
    private int quantidadeVendida;
    

    private Produto produto;
    DateTimeFormatter formato=DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Vendas (Produto produto){
        this.produto=produto;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Vendas (LocalDate data, String produtoVendido, int quantidadeVendida, Produto produto){
        this.data=data;
        this.produtoVendido=produtoVendido;
        this.quantidadeVendida=quantidadeVendida;
        this.produto=produto;
    }

    public LocalDate getData() {
        return data;
    }
    public void setData(LocalDate data) {
        this.data = data;
    }
    public String getProdutoVendido() {
        return produtoVendido;
    }
    public void setProdutoVendido(String produtoVendido) {
        this.produtoVendido = produtoVendido;
    }
    public int getQuantidadeVendida() {
        return quantidadeVendida;
    }
    public void setQuantidadeVendida(int quantidadeVendida) {
        this.quantidadeVendida = quantidadeVendida;
    }

    @Override
    public String toString() {
        return "Venda [Data da venda: " + data.format(formato) + ", Produto: " + produto.getNome() + ", Valor unitário: " + produto.getValor() + ", Quantidade: " + quantidadeVendida + "]";

     
    }
}
