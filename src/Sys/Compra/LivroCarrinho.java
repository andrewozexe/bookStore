package Sys.Compra;

import Sys.DataBase.LivroDAO;
import Sys.User;

import java.util.Scanner;

public class LivroCarrinho extends Livro {
    static Scanner in = new Scanner(System.in);
    private int quantia;
    private int estoque = getEstoque();
    private String cpf;
    private int cod_livro;
    private LivroDAO livroDAO = new LivroDAO();

    @Override
    public void setEstoque(int estoque) {
        estoque = livroDAO.buscaCod(cod_livro).getEstoque();
    }

    public void setCod_livro(int cod_livro) {
        this.cod_livro = cod_livro;
    }

    @Override
    public int getEstoque() {
        return estoque;
    }

    public String getCpf() {
        return cpf;
    }

    public int getCod_livro() {
        return cod_livro;
    }

    public void setCpf(User user) {
        this.cpf = user.toString();
    }
    public void setCpf(String cpf){
        this.cpf = cpf;
    }

    public LivroCarrinho(){}

    public LivroCarrinho(double preco, int estoque, String nome, String autor, int quantia) {
        super(preco, estoque, nome, autor);
        this.quantia = quantia;
    }
    public LivroCarrinho(double preco, int estoque, String nome, String autor) {
        super(preco, estoque, nome, autor);
    }

    public LivroCarrinho(double preco, int estoque, String nome, String autor, int quantia, int cod_livro) {
        super(preco, estoque, nome, autor);
        this.quantia = quantia;
        setCod(cod_livro);
    }
    public int getQuantia() {
        return quantia;
    }

    public void setQuantia(int quantia) {
        this.quantia = quantia;
}
    @Override
    public double getPreco() {
        return super.getPreco();
    }

    public void comprar(){
        if (quantia < estoque){
        estoque -= getQuantia();
        }
        else {
            System.out.println("Não há exemplares suficientes");
        }
        LivroDAO livroDAO = new LivroDAO();
        livroDAO.compra(cod_livro, estoque);
    }
}
