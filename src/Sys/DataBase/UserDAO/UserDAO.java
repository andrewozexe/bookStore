package Sys.DataBase.UserDAO;

import Sys.DataBase.Conect;
import Sys.User;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    public void save(@NotNull User user){
        // INSERT //
        String sql = "INSERT INTO usuarios(cpf, senha, nome) values(?,?,?)";
        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = Conect.getConection();
            assert conn != null;
            pstm = (PreparedStatement) conn.prepareStatement(sql);
            pstm.setString(1,user.getCpf());
            pstm.setString(2, user.getSenha());
            pstm.setString(3, user.getNome());

            pstm.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try{
                if (pstm != null){
                    pstm.close();
                }
                if (conn != null){
                    conn.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    public void update(User user) {
        String sql = "UPDATE usuarios SET cpf = ?, senha = ?, nome = ?" + " WHERE id = ?";
        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = Conect.getConection();
            assert conn != null;
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, user.getCpf());
            pstm.setString(2, user.getSenha());
            pstm.setString(3, user.getNome());
            pstm.setInt(4, user.getId());

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
    public void delete(int id){
        String sql = "DELETE FROM usuarios WHERE id = ?";
        Connection conn = null;
        PreparedStatement pstm = null;
         try{
             conn = Conect.getConection();
             assert conn != null;
             pstm = conn.prepareStatement(sql);
             pstm.setInt(1, id);
             pstm.execute();

         } catch (SQLException e) {
             e.printStackTrace();
         }finally {
             try {
                 if (pstm != null){
                     pstm.close();
                 }
                 if (conn != null){
                     conn.close();
                 }
             }catch (Exception e){
                 e.printStackTrace();
             }
         }
    }
    public List<User> getUsuarios(){
        // SELECT //
        String sql = "SELECT * FROM usuarios";
        List<User> usuarios = new ArrayList<>();

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;

        try {
            conn = Conect.getConection();
            assert conn != null;
            pstm = conn.prepareStatement(sql);
            rset = pstm.executeQuery();
            while (rset.next()){
                User user = new User();
                user.setCpf(rset.getString(1));
                user.setSenha(rset.getString(2));
                user.setNome(rset.getString(3));
                user.setId(rset.getInt(4));
                usuarios.add(user);


            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try{

                if (rset != null){
                    rset.close();
                }
                if (pstm != null){
                    pstm.close();
                }
                if (conn != null){
                    conn.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return usuarios;
    }

}
