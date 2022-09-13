package Sys.DataBase;

import Sys.User;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private Connection conn = null;
    private PreparedStatement pstm = null;
    private ResultSet rset = null;
    public void save(@NotNull User user) {
        // INSERT //
        String sql = "INSERT INTO usuarios(cpf, senha, nome) values(?,?,?)";
        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = Conect.getConection();
            assert conn != null;
            pstm = (PreparedStatement) conn.prepareStatement(sql);
            pstm.setString(1, user.getCpf());
            pstm.setString(2, user.getSenha());
            pstm.setString(3, user.getNome());

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

    public void update(User user) {
        String sql = "UPDATE usuarios SET cpf = ?, senha = ?, nome = ? WHERE cpf = ?";
        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = Conect.getConection();
            assert conn != null;
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, user.getCpf());
            pstm.setString(2, user.getSenha());
            pstm.setString(3, user.getNome());
            pstm.setString(4, user.getCpf());

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

    public void delete(int id) {
        String sql = "DELETE FROM usuarios WHERE id = ?";
        Connection conn = null;
        PreparedStatement pstm = null;
        try {
            conn = Conect.getConection();
            assert conn != null;
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, id);
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

    public List<User> getUsuarios() {
        // SELECT //
        String sql = "SELECT * FROM usuarios";
        List<User> usuarios = new ArrayList<>();


        try {
            conn = Conect.getConection();
            assert conn != null;
            pstm = conn.prepareStatement(sql);
            rset = pstm.executeQuery();
            while (rset.next()) {
                User user = new User();
                user.setCpf(rset.getString(1));
                user.setSenha(rset.getString(2));
                user.setNome(rset.getString(3));
                usuarios.add(user);
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
        return usuarios;
    }

    public List<String> getCPFs() {
        List<String> cpfs = new ArrayList<>();
        UserDAO userDAO = new UserDAO();
        for (User user : userDAO.getUsuarios()) {
            cpfs.add(user.getCpf());
        }
        return cpfs;
    }

    public List<String> getSenhas() {
        List<String> senhas = new ArrayList<>();
        UserDAO userDAO = new UserDAO();
        for (User user : userDAO.getUsuarios()) {
            senhas.add(user.getSenha());
        }
        return senhas;
    }

    public User buscaUsuario(String cpf) {
        // SELECT //
        String sql = "SELECT * FROM usuarios WHERE cpf = ?";
        User user = new User();
        try {
            conn = Conect.getConection();
            assert conn != null;
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, cpf);
            rset = pstm.executeQuery();
            while (rset.next()) {
                user.setCpf(rset.getString(1));
                user.setSenha(rset.getString(2));
                user.setNome(rset.getString(3));
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
        return user;
    }
}

