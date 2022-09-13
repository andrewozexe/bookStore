package Sys.DataBase;

import Sys.Compra.Livro;
import Sys.DataBase.Conect;
import Sys.User;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LivroDAO {

    private Connection conn = null;
    private PreparedStatement pstm = null;
    private ResultSet rset = null;

    public void save(@NotNull Livro livro, User user) {
        // INSERT //
        String sql = "INSERT INTO livros(preco, estoque, nome, autor) values(?,?,?,?)";

        try {
            conn = Conect.getConection();
            assert conn != null;
            pstm = conn.prepareStatement(sql);
            pstm.setDouble(1, livro.getPreco());
            pstm.setInt(2, livro.getEstoque());
            pstm.setString(3, livro.getNome());
            pstm.setString(4, livro.getAutor());

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

    public void update(Livro livro) {
        String sql = "UPDATE livros SET  preco = ?, estoque = ?, nome = ?, autor = ?  WHERE cod = ?";
        try {
            conn = Conect.getConection();
            assert conn != null;
            pstm = conn.prepareStatement(sql);
            pstm.setInt(5, livro.getCod());
            pstm.setDouble(1, livro.getPreco());
            pstm.setInt(2, livro.getEstoque());
            pstm.setString(3, livro.getNome());
            pstm.setString(4, livro.getAutor());
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

    public void compra(int cod, int estoque) {
        String sql = "UPDATE livros SET estoque = ? WHERE cod = ?";
        try {
            conn = Conect.getConection();
            assert conn != null;
            pstm = conn.prepareStatement(sql);
            pstm.setInt(2, cod);
            pstm.setInt(1, estoque);

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

    public void delete(int cod) {
        String sql = "DELETE FROM livros WHERE cod = ?";
        try {
            conn = Conect.getConection();
            assert conn != null;
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, cod);
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
    @NotNull
    public List<Livro> getLivros() {
        String sql = "SELECT * FROM livros";
        List<Livro> livros = new ArrayList<>();

        try {
            conn = Conect.getConection();
            assert conn != null;
            pstm = conn.prepareStatement(sql);
            rset = pstm.executeQuery();
            while (rset.next()){
                Livro livro = new Livro();
                livro.setCod(rset.getInt(1));
                livro.setPreco(rset.getDouble(2));
                livro.setEstoque(rset.getInt(3));
                livro.setNome(rset.getString(4));
                livro.setAutor(rset.getString(5));
                livros.add(livro);
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

    public List<String> getNomes(){
        List<String> nomesLivros = new ArrayList<>();
        for (Livro livro: getLivros()){
            nomesLivros.add(livro.getNome());
        }
        return nomesLivros;
    }

    public List<String> getAutores(){
        List<String> autores = new ArrayList<>();
        for (Livro livro: getLivros()){
            autores.add(livro.getAutor());
        }
        return autores;
    }

    public Livro buscaNome(String nome) {
        // SELECT //
        String sql = "SELECT * FROM livros WHERE nome = ?";
        Livro livro = new Livro();
        try {
            conn = Conect.getConection();
            assert conn != null;
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, nome);
            rset = pstm.executeQuery();
            while (rset.next()){
                livro.setCod(rset.getInt(1));
                livro.setPreco(rset.getDouble(2));
                livro.setEstoque(rset.getInt(3));
                livro.setNome(rset.getString(4));
                livro.setAutor(rset.getString(5));
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
        return livro;
    }
    public Livro buscaCod(int cod) {
        // SELECT //
        String sql = "SELECT * FROM livros WHERE cod = ?";
        Livro livro = new Livro();
        try {
            conn = Conect.getConection();
            assert conn != null;
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, cod);
            rset = pstm.executeQuery();
            while (rset.next()){
                livro.setCod(rset.getInt(1));
                livro.setPreco(rset.getDouble(2));
                livro.setEstoque(rset.getInt(3));
                livro.setNome(rset.getString(4));
                livro.setAutor(rset.getString(5));
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
        return livro;
    }

    @NotNull
    public static List<Livro> getLivros(String nome, String sql, Connection conn, PreparedStatement pstm, ResultSet rset) {
        List<Livro> livros = new ArrayList<>();
        try {
            conn = Conect.getConection();
            assert conn != null;
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, nome);
            rset = pstm.executeQuery();
            while (rset.next()){
                Livro livro = new Livro();
                livro.setCod(rset.getInt(1));
                livro.setPreco(rset.getDouble(2));
                livro.setEstoque(rset.getInt(3));
                livro.setNome(rset.getString(4));
                livro.setAutor(rset.getString(5));
                livros.add(livro);
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
    public void mostrarLivros(@NotNull List<Livro> livros){
        int i = 0;
        for (Livro livro : livros){
            System.out.printf("%d -> %s, %s : R$%.2f \n", i, livro.getNome(), livro.getAutor(), livro.getPreco());
            i++;
        }
    }
}


