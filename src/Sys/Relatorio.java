package Sys;

import Sys.DataBase.UserDAO;

import java.util.List;
import java.util.Objects;

public class Relatorio {
    private String cpf;
    private double valor;

    private UserDAO userDAO = new UserDAO();
    private List<User> users = userDAO.getUsuarios();
    private User usuario;

    public Relatorio(String cpf, double valor) {
        this.cpf = cpf;
        this.valor = valor;
    }

    public void setUser(){
        for (User user : users){
            if (Objects.equals(cpf, user.getCpf())){
                usuario = user;
            }
        }
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Relatorio(){}


    @Override
    public String toString() {
        return "Usuário: %s\nCPF: %s\nCompra no total de: RS%.2f".formatted(usuario.getNome(),cpf,getValor());
    }
}
