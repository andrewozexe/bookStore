
package Sys;

import java.util.Objects;

public class User {
    private int id;
    private String cpf;
    private String senha;
    private String nome;


    public User(int id, String cpf, String senha, String nome) {
        this.id = id;
        this.cpf = cpf;
        this.senha = senha;
        this.nome = nome;
    }
    public User(String cpf, String senha, String nome){
        this.cpf = cpf;
        this.senha = senha;
        this.nome = nome;
    }
    public User(String cpf, String senha) {
        this.cpf = cpf;
        this.senha = senha;
    }
    public User() {}

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public boolean equals(Object obj) {
        if (Objects.equals(this.cpf, ((User) obj).cpf)
                && Objects.equals(this.senha, ((User) obj).senha)
                || this.id == ((User) obj).id) {return true;} return false;}
    @Override
    public String toString() {
        return cpf;
    }
    }
