package Sys.DataBase;

import Sys.Compra.Livro;
import Sys.Compra.LivroCarrinho;
import Sys.Relatorio;
import Sys.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class RelatorioDAO {
    private Connection conn = null;
    private PreparedStatement pstm = null;
    private ResultSet rset = null;

    public void add(User user, CarrinhoDAO carrinhoDAO) {
        String sql = "INSERT INTO relatorio(cpf, valor) VALUES(?,?)";
        try {
            conn = Conect.getConection();
            assert conn != null;
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, user.getCpf());
            pstm.setDouble(2, carrinhoDAO.totalCarrinho(user));
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

    public List<String> getRelatorio() {
        String sql = "SELECT * FROM relatorio";
        List<String> relatorio = new ArrayList<>();
        try {
            conn = Conect.getConection();
            assert conn != null;
            pstm = conn.prepareStatement(sql);
            rset = pstm.executeQuery();
            while (rset.next()) {
                Relatorio relatorio1 = new Relatorio();
                relatorio1.setCpf(rset.getString(1));
                relatorio1.setValor(rset.getDouble(2));
                relatorio1.setUser();
                relatorio.add(relatorio1.toString());
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
        return relatorio;
    }

}
