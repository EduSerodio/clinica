package com.dh.clinica.service.impl;

import com.dh.clinica.config.ConfiguracaoJDBC;
import com.dh.clinica.model.Usuario;
import com.dh.clinica.service.IDao;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioDaoImpl implements IDao<Usuario> {

    private ConfiguracaoJDBC configuracaoJDBC;

    public UsuarioDaoImpl() {
        this.configuracaoJDBC = new ConfiguracaoJDBC();
    }

    final static Logger log = Logger.getLogger(DentistaServiceImpl.class);

    @Override
    public Usuario salvar(Usuario usuario) {
        Connection connection = configuracaoJDBC.conectarComBancoDeDados();
        Statement stmt = null;
        String query = String.format("INSERT INTO USUARIO (NOME ,EMAIL, SENHA, NIVEL_ACESSO) " +
                        "VALUES ('%s','%s','%s','%s')", usuario.getNome(),
                usuario.getEmail(), usuario.getSenha(), usuario.getNivelAcesso());
        try {
            stmt = connection.createStatement();
            stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next())
                usuario.setId(keys.getInt(1));
            stmt.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return usuario;
    }

    @Override
    public List<Usuario> buscarTodos() {
        Connection connection = configuracaoJDBC.conectarComBancoDeDados();
        Statement stmt = null;
        String query = "SELECT * FROM USUARIO";
        List<Usuario> usuarios = new ArrayList<>();
        try {
            stmt = connection.createStatement();
            ResultSet result = stmt.executeQuery(query);
            while (result.next()) {
                usuarios.add(criarObjetoUsuario(result));
            }
            stmt.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return usuarios;
    }

    // MÉTODO IMPLEMENTADO DA INTERFACE "IDAO" PARA BUSCAR UM PACIENTE PELO ID
    @Override
    public Optional<Usuario> buscarPorId(Integer id) {
        log.debug("Buscando Usuário com id: " + id);
        Connection connection = configuracaoJDBC.conectarComBancoDeDados();
        Statement statement = null;
        String query = String.format("SELECT * FROM usuario WHERE id = '%s'", id);
        Usuario usuario = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                usuario = criarObjetoUsuario(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario != null ? Optional.of(usuario) : Optional.empty();
    }

    @Override
    public Usuario atualizar (Usuario usuario) {
        log.debug("Atualizando um Usuario: " + usuario.toString());
        Connection connection = configuracaoJDBC.conectarComBancoDeDados();
        Statement stmt = null;
        String query = String.format("UPDATE USUARIO SET NOME = '%s', EMAIL = '%s', SENHA = '%s', NIVEL_ACESSO = '%s', WHERE ID = '%s'",
                usuario.getNome(), usuario.getEmail(), usuario.getSenha(), usuario.getNivelAcesso(), usuario.getId());
        try {
            stmt = connection.createStatement();
            stmt.executeUpdate(query);
            stmt.close();
            connection.close(); }
        catch (SQLException throwables) {
            throwables.printStackTrace(); }
        return usuario; }


    @Override
    public void excluir(Integer id) {
        Connection connection = configuracaoJDBC.conectarComBancoDeDados();
        Statement stmt = null;
        String query = String.format("DELETE FROM USUARIO WHERE ID = %s", id);
        try {
            stmt = connection.createStatement();
            stmt.executeUpdate(query);
            stmt.close();
            connection.close();
        } catch (SQLException throwables) {

        }
    }

    private Usuario criarObjetoUsuario(ResultSet resultado) throws SQLException {
        Integer id = resultado.getInt("ID");
        String nome = resultado.getString("NOME");
        String email = resultado.getString("EMAIL");
        String senha = resultado.getString("SENHA");
        String nivelAcesso = resultado.getString("NIVEL_ACESSO");
        Usuario usuario = new Usuario(id, nome, email, senha, nivelAcesso);
        return usuario;
    }
}
