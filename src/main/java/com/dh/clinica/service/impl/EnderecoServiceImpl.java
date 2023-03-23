package com.dh.clinica.service.impl;

import com.dh.clinica.config.ConfiguracaoJDBC;
import com.dh.clinica.model.Endereco;
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
public class EnderecoServiceImpl implements IDao<Endereco> {

    private ConfiguracaoJDBC configuracaoJDBC;

    final static Logger log = Logger.getLogger(EnderecoServiceImpl.class);

    @Override
    public Endereco salvar(Endereco endereco) {
        log.debug("Salvando novo endereço: " + endereco.toString());
        Connection connection = configuracaoJDBC.conectarComBancoDeDados();
        Statement statement = null;
        String query = String.format("INSERT INTO endereco (rua, numero, cidade, estado) VALUES " +
                "('%s', '%s', '%s', '%s')", endereco.getRua(), endereco.getNumero(), endereco.getCidade(), endereco.getEstado());
        try {
            statement = connection.createStatement();
            statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next())
                endereco.setId(generatedKeys.getInt(1));
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return endereco;
    }

    @Override
    public List<Endereco> buscarTodos() {
        log.debug("Buscando enderecos cadastrados...");
        Connection connection = configuracaoJDBC.conectarComBancoDeDados();
        Statement statement = null;
        String query = "SELECT * FROM endereco";
        List<Endereco> enderecos = new ArrayList<>();

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                enderecos.add(criarEndereco(resultSet));
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return enderecos;
    }

    // MÉTODO IMPLEMENTADO DA INTERFACE "IDAO" PARA BUSCAR ENDEREÇO POR ID
    @Override
    public Optional<Endereco> buscarPorId(Integer id) {
        log.debug("Buscando endereco com id: " + id);
        Connection connection = configuracaoJDBC.conectarComBancoDeDados();
        Statement statement = null;
        String query = String.format("SELECT * FROM endereco WHERE id = '%s'", id);
        Endereco endereco = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                endereco = criarEndereco(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return endereco!= null ? Optional.of(endereco) : Optional.empty();
    }

    @Override
    public Endereco atualizar (Endereco endereco) {
        log.debug("Atualizando um Endereço: " + endereco.toString());
        Connection connection = configuracaoJDBC.conectarComBancoDeDados();
        Statement stmt = null;
        String query = String.format("UPDATE ENDERECO SET RUA = '%s', NUMERO = '%s', CIDADE = '%s', ESTADO  = '%s', WHERE ID = '%s'",
                endereco.getRua(), endereco.getNumero(), endereco.getCidade(), endereco.getEstado(), endereco.getId());
        try {
            stmt = connection.createStatement();
            stmt.executeUpdate(query);
            stmt.close();
            connection.close(); }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
            return endereco;
    }

    @Override
    public void excluir(Integer id) {
        log.debug("Excluindo endereco com id: " + id);
        Connection connection = configuracaoJDBC.conectarComBancoDeDados();
        Statement statement = null;
        String query = String.format("DELETE FROM endereco WHERE id = '%s'", id);
        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // MÉTODO CRIADO PARA CRIAR ENDEREÇOS PARA ADICIONAR NO ARRAYLIST
    public Endereco criarEndereco(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String rua = resultSet.getString("rua");
        String numero = resultSet.getString("numero");
        String cidade = resultSet.getString("cidade");
        String bairro = resultSet.getString("bairro");
        Endereco endereco = new Endereco(id, rua, numero, cidade, bairro);
        return endereco;
    }



}
