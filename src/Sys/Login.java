package Sys;

import Sys.DataBase.UserDAO;


import java.util.*;

public class Login {
    private UserDAO userDAO = new UserDAO();
    private List<String> cpfs = userDAO.getCPFs();
    private List<String> senhas = userDAO.getSenhas();

    static Scanner in = new Scanner(System.in);

    public HashMap<String, String> uCS() {
        HashMap<String, String> uCS = new HashMap<>();
        for (int i = 0; i < cpfs.size(); i++) {
            uCS.put(cpfs.get(i), senhas.get(i));
        }
        return uCS;
    }
    public boolean verific(String cpf, String senha) {
        for (String cpf_e : uCS().keySet()) {
            if ((cpfs.contains(cpf) && !(senhas.contains(senha)))) {
                return false;
            }else if (!(cpfs.contains(cpf)) && senhas.contains(senha)){
                return false;
            }else if (!(cpfs.contains(cpf)) && !senhas.contains(senha)) {
                return false;
            }else {
                break;
            }
        }
        return true;
    }
    public User cadastro() {
        //Cadastrar novo usuário
        System.out.println("Usuário não cadastrado!\nPara fazer seu cadastro, siga os passos:");
        System.out.print("Digite seu nome: ");
        String nome = in.nextLine();
        System.out.print("Digite seu CPF: ");
        String cpf = in.nextLine();
        System.out.print("Digite sua senha:");
        String senha = in.nextLine();
        User usuario = new User(cpf, senha, nome);
        userDAO.save(usuario);
        System.out.println("Cadastrado com sucesso!");
        return usuario;
    }



    public List<String> getCpfs() {
        return cpfs;
    }


    public List<String> getSenhas() {
        return senhas;
    }

}