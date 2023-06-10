import java.io.DataInput;
import java.time.LocalDate;
import java.util.Date;

public class Vendas {
    private LocalDate data;
    private String produtoVendido;
    private int quantidadeVendida;

    public Vendas (LocalDate data, String produtoVendido, int quantidadeVendida){
        this.data=data;
        this.produtoVendido=produtoVendido;
        this.quantidadeVendida=quantidadeVendida;
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
        return "Venda [Produto: " + produtoVendido + ", Quantidade: " + quantidadeVendida + ", Data da venda: " + data + "]";
    }
}
