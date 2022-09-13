package Sys.DataBase;


import Sys.Compra.Livro;
import Sys.Compra.LivroCarrinho;
import Sys.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class CarrinhoDAO {

    private Connection conn = null;
    private PreparedStatement pstm = null;
    private ResultSet rset = null;

    private static LivroDAO livroDAO = new LivroDAO();

    public LivroCarrinho toLivroCarrinho(Livro livro, int quantia, int cod) {
        return new LivroCarrinho(
                livro.getPreco(),
                livro.getEstoque(),
                livro.getNome(),
                livro.getAutor(),
                quantia,
                cod);
    }

    public void add(LivroCarrinho livro, User user) {
        String sql = "INSERT INTO carrinho(cpf, qnt_livros, cod_livro, valor) VALUES(?,?,?,?)";
        try {
            conn = Conect.getConection();
            assert conn != null;
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, user.getCpf());
            pstm.setInt(2, livro.getQuantia());
            pstm.setInt(3, livro.getCod());
            pstm.setDouble(4, livro.getPreco());
            pstm.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstm != null) {
                    pstm.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public List<LivroCarrinho> livrosNoCarrinho(User user) {
        String sql = "SELECT * FROM carrinho WHERE cpf = ?";
        List<LivroCarrinho> livros = new ArrayList<>();
        try {
            conn = Conect.getConection();
            assert conn != null;
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, user.getCpf());
            rset = pstm.executeQuery();
            while (rset.next()) {
                LivroCarrinho livroCarrinho = new LivroCarrinho();
                livroCarrinho.setCpf(user.getCpf());
                livroCarrinho.setQuantia(rset.getInt(2));
                livroCarrinho.setCod_livro(rset.getInt(3));
                livroCarrinho.setPreco(rset.getDouble(4));
                pegaLivro(livroCarrinho);
                livros.add(livroCarrinho);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rset != null) {
                    rset.close();
                }
                if (pstm != null) {
                    pstm.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return livros;
    }

    public void mostrarLivrosNoCarrinho(User user) {
        System.out.println("Livros no carrinho: ");
        for (LivroCarrinho livroCarrinho : livrosNoCarrinho(user)) {
            System.out.printf("%s\n%s\nR$%.2f\nQuantia: %d\n", livroCarrinho.getNome(), livroCarrinho.getAutor(), livroCarrinho.getPreco(), livroCarrinho.getQuantia());
        }
    }

    public void updateCarrinho(User user, LivroCarrinho livro) {
        String sql = "UPDATE carrinho SET qnt_livros = ? WHERE cpf = ? AND cod_livro = ?";

        try {
            conn = Conect.getConection();
            assert conn != null;
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, livro.getQuantia() + getQuantia(user, livro));
            pstm.setString(2, user.getCpf());
            pstm.setInt(3, livro.getCod_livro());
            pstm.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {

                if (pstm != null) {
                    pstm.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public int getQuantia(User user, LivroCarrinho livro) {
        List<LivroCarrinho> livros = livrosNoCarrinho(user);
        for (int i = 0; i < livros.size(); i++) {
            if (Objects.equals(livros.get(i).getCpf(), livro.getCpf()) && livros.get(i).getCod_livro() == livro.getCod_livro()) {
                return livros.get(i).getQuantia();
            }
        }
        return 0;
    }
    public void delete(String cpf) {
        String sql = "DELETE FROM carrinho WHERE cpf = ?";
        Connection conn = null;
        PreparedStatement pstm = null;
        try {
            conn = Conect.getConection();
            assert conn != null;
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, cpf);
            pstm.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstm != null) {
                    pstm.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void pegaLivro(LivroCarrinho livroCarrinho) {
        List<Livro> livros = livroDAO.getLivros();
        for (Livro livro : livros) {
            if (livroCarrinho.getCod_livro() == livro.getCod()) {
                livroCarrinho.setNome(livro.getNome());
                livroCarrinho.setAutor(livro.getAutor());
                livroCarrinho.setEstoque(livro.getEstoque());
            }
        }
    }
    public void finalizarCompra(User user) {
        for (LivroCarrinho livro : livrosNoCarrinho(user)){
            livro.comprar();
            delete(user.getCpf());
        }

    }

    public double totalCarrinho(User user) {
        int total = 0;
        for (LivroCarrinho livro : livrosNoCarrinho(user)) {
            double preco = livro.getPreco();
            total += preco;

        }
        return total;
    }
}
