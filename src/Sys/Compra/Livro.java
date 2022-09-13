package Sys.Compra;

public class Livro {
    private double preco;
    private int estoque;
    private int cod;
    private String nome;
    private String autor;

    public Livro(double preco, int estoque, String nome, String autor) {
        this.preco = preco;
        this.estoque = estoque;
        this.nome = nome;
        this.autor = autor;
    }
    public Livro() {
    }
    public double getPreco() {
        return preco;
    }
    public int getCod() {
        return cod;
    }

    public String getNome() {
        return nome;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "Nome: %s\nAutor: %s\nPreço: %.2f\nCódigo: %d".formatted(getNome(),getAutor(),getPreco(),getCod());
    }
}
