package com.dh.clinica.service.impl;
import com.dh.clinica.config.ConfiguracaoJDBC;
import com.dh.clinica.model.Endereco;
import com.dh.clinica.model.Paciente;
import com.dh.clinica.service.IDao;
import com.dh.clinica.service.PacienteService;
import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.Optional;

import com.dh.clinica.util.Util;
import org.springframework.stereotype.Service;

@Service
public class PacienteDaoImpl implements IDao<Paciente> {

    private ConfiguracaoJDBC configuracaoJDBC;

    private EnderecoServiceImpl enderecoServiceImpl;

    final static Logger log = Logger.getLogger(EnderecoServiceImpl.class);

    public PacienteDaoImpl(EnderecoServiceImpl enderecoServiceImpl) {
        this.configuracaoJDBC = new ConfiguracaoJDBC();
        this.enderecoServiceImpl = enderecoServiceImpl;
    }

    // MÉTODO IMPLEMENTADO DA INTERFACE "IDAO" PARA SALVAR UM PACIENTE
    @Override
    public Paciente salvar(Paciente paciente) {
        log.debug("Salvando novo paciente: " + paciente.toString());
        Connection connection = configuracaoJDBC.conectarComBancoDeDados();
        Statement statement = null;
        paciente.setEndereco(enderecoServiceImpl.salvar(paciente.getEndereco()));
        String query = String.format("INSERT INTO paciente " +
                        "(nome, sobrenome, rg, data_nascimento, endereco_id)" +
                        " VALUES ('%s', '%s', '%s', '%s', '%s')",
                paciente.getNome(),
                paciente.getSobrenome(),
                paciente.getRg(),
                Util.dateToTimestamp(paciente.getDataNascimento()),
                paciente.getEndereco().getId());
        try {
            statement = connection.createStatement();
            statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next())
                paciente.setId(generatedKeys.getInt(1));
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return paciente;
    }

    @Override
    public List<Paciente> buscarTodos() {
        log.debug("Buscando todos os pacientes");
        Connection conexao = configuracaoJDBC.conectarComBancoDeDados();
        Statement stmt = null;
        String query = "SELECT * FROM paciente";
        List<Paciente> pacientes = new ArrayList<>();
        try {
            stmt = conexao.createStatement();
            ResultSet resultado = stmt.executeQuery(query);
            while (resultado.next()) {
                pacientes.add(criarObjetoPaciente(resultado));
            }

            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return pacientes;
    }

     //MÉTODO IMPLEMENTADO DA INTERFACE "IDAO" PARA BUSCAR UM PACIENTE PELO ID
    @Override
    public Optional<Paciente> buscarPorId(Integer id) {
        log.debug("Buscando paciente com id  : " + id);
        Connection conexao = configuracaoJDBC.conectarComBancoDeDados();
        Statement stmt = null;
        String query = String.format("SELECT ID, NOME, SOBRENOME, RG, DATA_NASCIMENTO, ENDERECO_ID FROM PACIENTE WHERE ID = '%s'", id);
        Paciente paciente = null;
        try {
            stmt = conexao.createStatement();
            ResultSet resultado = stmt.executeQuery(query);
            while (resultado.next()) {
                paciente = criarObjetoPaciente(resultado);
            }

            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return paciente != null ? Optional.of(paciente) : Optional.empty();
    }

    @Override
    public Paciente atualizar (Paciente paciente) {
        log.debug("Atualizando um paciente: " + paciente.toString());
        Connection connection = configuracaoJDBC.conectarComBancoDeDados();
        Statement stmt = null;
        String query = String.format("UPDATE PACIENTE SET NOME = '%s', SOBRENOME = '%s', RG = '%s', DATA_NASCIMENTO = '%s', ENDERECO = '%s' WHERE ID = '%s'",
                paciente.getNome(), paciente.getSobrenome(), paciente.getRg(), paciente.getDataNascimento(), paciente.getEndereco(), paciente.getId());
        try {
            stmt = connection.createStatement();
            stmt.executeUpdate(query);
            stmt.close();
            connection.close(); }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
             return paciente;
    }

    @Override
    public void excluir(Integer id) {
        log.debug("Excluindo paciente com id: " + id);
        Connection conexao = configuracaoJDBC.conectarComBancoDeDados();
        Statement stmt = null;
        String query = String.format("DELETE FROM paciente WHERE id = %s", id);
        try {
            stmt = conexao.createStatement();
            stmt.executeUpdate(query);
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    // MÉTODO CRIADO PARA CRIAR PACIENTES PARA ADICIONAR NO ARRAYLIST
    private Paciente criarObjetoPaciente(ResultSet result) throws SQLException {
        Integer idPaciente = result.getInt("id");
        String nome = result.getString("nome");
        String sobrenome = result.getString("sobrenome");
        String rg = result.getString("rg");
        Date dataCadastro = result.getDate("data_nascimento");
        Endereco endereco = enderecoServiceImpl.buscarPorId(result.getInt("endereco_id")).orElse(null);
        Paciente paciente = new Paciente(idPaciente, nome, sobrenome, rg, dataCadastro, endereco);
        return paciente;
    }


}
