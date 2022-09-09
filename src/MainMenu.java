
import Sys.Login;
import Sys.User;
import Sys.DataBase.UserDAO.UserDAO;

import java.util.Objects;
import java.util.Scanner;



public class MainMenu {
    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        Login login = new Login();

        System.out.println("Bem Vindo(a) a Books SA.\nFaça seu login ou seu cadastro para continuar: ");
        System.out.print("Digite seu CPF: ");
        String cpf = in.nextLine();
        System.out.print("Digite sua senha: ");
        String senha = in.nextLine();

        User usuario = new User(cpf,senha);
        login.cadastro(usuario);

    }
}

