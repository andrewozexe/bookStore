
package Sys;


public class User {
    private int id;
    private String cpf;
    private String senha;
    private String nome;

    private int admin;

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

    public User() {

    }

    public String getNome() {
        return nome;
    }

    public int getId() {
        return id;
    }
    public String getCpf() {
        return cpf;
    }

    public String getSenha() {
        return senha;
    }

    public int getAdmin() {
        return admin;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setAdmin(int admin) {
        this.admin = admin;
    }
}
