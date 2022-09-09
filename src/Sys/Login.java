package Sys;

import Sys.DataBase.UserDAO.UserDAO;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Login {
    UserDAO userDAO = new UserDAO();
    List<User> users = userDAO.getUsuarios();
    static Scanner in = new Scanner(System.in);
    public void cadastro(User user) {
        for (User u : users){
            if (u.getCpf().equals(user.getCpf()) || u.getId() == user.getId()) {
                if (Objects.equals(u.getNome(), "admin") || u.getAdmin() == 1){
                    System.out.println("Conta de Administrador encontrada...\nENTRANDO NO MODO ADMIN...");
                }else {
                    System.out.println("Entrnado...");
                }
            } else {
                System.out.println("Usuário não cadastrado!\nPara fazer seu cadastro, siga os passos:");
                System.out.print("Digite seu nome: ");
                String nome = in.nextLine();
                System.out.print("Digite seu CPF: ");
                String cpf = in.nextLine();
                System.out.print("Digite sua senha:");
                String senha = in.nextLine();

                User usuario = new User(cpf,senha, nome);
                userDAO.save(usuario);
                System.out.println("Cadastrado com sucesso!");
            }
            break;
        }

    }
}
