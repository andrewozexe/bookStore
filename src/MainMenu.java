import Sys.Menu;
import Sys.Login;
import Sys.User;

import java.util.Objects;
import java.util.Scanner;

public class MainMenu {
    static Scanner in = new Scanner(System.in);
    public static void main(String[] args) {
        Login login = new Login();
        User usuario = null;
        Menu menu = new Menu();
        System.out.println("Bem Vindo(a) a Books SA.\nFaça seu login ou seu cadastro para continuar: ");

        int escolha = 1;
        usuario = menu.entrar();
        while (escolha != 0) {
            if (Objects.equals(usuario.getCpf(), "admin")) {
                System.out.println(
                        "[1] Fazer compras\n" +
                        "[2] Trocar usuário\n" +
                        "[3] Sobre\n" +
                        "[4] Relatório sobre clientes\n" +
                        "[0] Sair");
            } else {
                System.out.println(
                        "[1] Fazer compras\n" +
                        "[2] Trocar usuário\n" +
                        "[3] Sobre\n" +
                        "[0] Sair");
            }
            escolha = Integer.parseInt(in.nextLine());
            if (escolha == 2) {
                System.out.println("Troca de usuário: ");
                usuario = menu.entrar();
            } else {
                menu.menu(escolha, usuario);
            }
        }
        System.out.println("SAINDO...");
        System.out.println("Obrigado, volte sempre!");
    }
}

