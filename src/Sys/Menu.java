package Sys;

import Sys.Compra.*;
import Sys.DataBase.CarrinhoDAO;
import Sys.DataBase.LivroDAO;
import Sys.DataBase.RelatorioDAO;
import Sys.DataBase.UserDAO;
import org.jetbrains.annotations.NotNull;


import java.util.List;
import java.util.Scanner;

public class Menu {

    static Scanner in = new Scanner(System.in);
    private final Login login = new Login();
    private User user = new User();
    private UserDAO userDAO = new UserDAO();

    public User getUser() {
        return user;
    }


    public void setUser(User user) {
        this.user = user;
    }

    private LivroDAO livroDAO = new LivroDAO();
    private int escolha;
    private CarrinhoDAO carrinhoDAO = new CarrinhoDAO();
    private RelatorioDAO relatorioDAO = new RelatorioDAO();
    public User entrar(){
        System.out.print("Digite seu CPF: ");
        String cpf = in.nextLine();
        System.out.print("Digite sua senha: ");
        String senha = in.nextLine();
        user = userDAO.buscaUsuario(cpf);
        setUser(user);
        if (!(login.getCpfs().contains(cpf))) {
            user = login.cadastro();
            setUser(user);
        }else {
            while (!login.verific(cpf, senha)) {
                System.out.println("Usuário ou senha incorretos");
                System.out.print("Digite seu CPF: ");
                cpf = in.nextLine();
                System.out.print("Digite sua senha: ");
                senha = in.nextLine();
                user = userDAO.buscaUsuario(cpf);
                System.out.printf("%s%n", user.getNome());
            }
        }
        return user;
    }


    public void addCarrinho(List<Livro> livros){
        LivroCarrinho livroCarrinho = new LivroCarrinho();
        colocarNoCarrinho(livros);
    }
    private void colocarNoCarrinho(List<Livro> livros) {
        LivroCarrinho livroCarrinho;
        System.out.println("Deseja adicionar algum livro ao carrinho?\n[1] SIM\n[2] NÃO");
        int add = Integer.parseInt(in.nextLine());
        if (add == 1){
            int i = 1;
            int indexLivro, quantia;
            while (i != 2){
                System.out.println("Digite codigo do livro que dejesa adicionar");
                indexLivro = Integer.parseInt(in.nextLine());
                System.out.printf("Livro: %s%nCodigo: %d%n", livros.get(indexLivro).getNome(), livros.get(indexLivro).getCod());

                System.out.println("Digite a quantia que deseja adicionar");
                quantia = Integer.parseInt(in.nextLine());

                livroCarrinho = carrinhoDAO.toLivroCarrinho(livros.get(indexLivro), quantia, livros.get(indexLivro).getCod());
                System.out.println(livroCarrinho.getCod());
                carrinhoDAO.add(livroCarrinho, user);
                System.out.println("Deseja adicionar mais livros?\n[1] SIM\n[2] NÃO");
                i = Integer.parseInt(in.nextLine());
                //percorrer a lista de todos os livros comparando o "escolhaLivro com o indice da lista "livros"
                //adicionar o livro encontrado ao carrinho
            }
        }
    }
    private void colocarNoCarrinho(Livro livro){
        LivroCarrinho livroCarrinho;
        System.out.printf("Livro %s encontrado!\nAutor: %s\nCodigo: %s\nPreço: %.2f\n", livro.getNome(),livro.getAutor(),livro.getCod(),livro.getPreco());
        System.out.println("Deseja adicionar o livro ao carrinho?\n[1] SIM\n[2] NÃO");
        int add = Integer.parseInt(in.nextLine());
        if (add == 1){
                System.out.printf("Livro: %s%nCodigo: %d%n", livro.getNome(), livro.getCod());
                System.out.println("Digite a quantia que deseja adicionar");
                int quantia = Integer.parseInt(in.nextLine());
                livroCarrinho = carrinhoDAO.toLivroCarrinho(livro, quantia, livro.getCod());
                System.out.printf("Quantidade no carrinho: " +
                        "%d\n",carrinhoDAO.getQuantia(user,livroCarrinho));
                if (carrinhoDAO.getQuantia(user,livroCarrinho) > 0){
                    carrinhoDAO.updateCarrinho(user, livroCarrinho);
                } else {
                    System.out.println(livroCarrinho.getCod());
                    carrinhoDAO.add(livroCarrinho, user);
                    System.out.println("Livro Adicionado!");
                }
        } else {
            System.out.println("Voltando ao menu...");
        }
    }
    public void menu(int escolha, @NotNull User usuario){
        this.escolha = escolha;
        user = usuario;
        switch (escolha){
            case 1 -> {
                Pagina pagina = new Pagina(livroDAO.getLivros());
                int esc;
                do {
                    System.out.println(" -- COMPRAS --\nQual operação deseja realizar?");
                    System.out.println(
                            "[1] Listar todos os livros\n" +
                            "[2] Buscar livro por nome\n" +
                            "[3] Exibir Carrinho\n" +
                            "[0] Voltar ao menu principal");
                    esc = Integer.parseInt(in.nextLine());
                    switch (esc) {
                        case 1 -> {
                            int naveg;
                            do {
                                pagina.imp();
                                System.out.println("NAVEGAÇÃO\n<<[1]Voltar | Avançar[2]>>\n[0]Sair");
                                naveg = Integer.parseInt(in.nextLine());
                                if (naveg == 1) {
                                    pagina.voltar();
                                }else if (naveg == 2){
                                    pagina.avancar();
                                }
                                addCarrinho(livroDAO.getLivros());
                            } while (naveg != 0);
                        }
                        case 2 -> {
                            System.out.println("Digite o titulo do livro");
                            String nome = in.nextLine();
                            if (livroDAO.buscaNome(nome) != null) {
                                Livro livrobusca = livroDAO.buscaNome(nome);
                                colocarNoCarrinho(livrobusca);

                            } else {
                                System.out.println("Nenhum Livro encontrado com esse Titulo");
                            }
                        }
                        case 3 -> { //editar carrinho
                            Pagina paginaCarrinhbo = new Pagina();
                            carrinhoDAO.mostrarLivrosNoCarrinho(user);
                            System.out.println(carrinhoDAO.totalCarrinho(user));
                            System.out.printf("Finalizar compra?\n[1]SIM\n[2]NÃO\n");
                            int escCompra = Integer.parseInt(in.nextLine());
                            if (escCompra == 1){
                                carrinhoDAO.finalizarCompra(user);
                                relatorioDAO.add(user,carrinhoDAO);
                            }
                        }
                    }
                } while (esc != 0);}
                case 3 -> {
                    System.out.println("SOBRE:");
                    System.out.printf(
                            "Desenvolvedor: André Fabricio Wozniack\n" +
                            "Data de lançamento: 13/09/2022" +
                            "Versão: 1.0" +
                            "Contexto: Programa desenvolvido para simular\n" +
                                    "uma loja de livros online com usuario e\n" +
                                    "sistema de carrinho de compras.\n"
                    );
                }
                case 4 -> {
                    if (usuario.getCpf().equals("admin")){
                        System.out.println(relatorioDAO.getRelatorio());
                        System.out.println("[1] Compras\n[2] Menu Principal");
                        escolha = Integer.parseInt(in.nextLine());
                    }
                }
                case 0 ->{
                    break;
                }

            }
        }
    }
