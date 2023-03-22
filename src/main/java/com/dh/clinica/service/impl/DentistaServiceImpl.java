package com.dh.clinica.service.impl;
import com.dh.clinica.config.ConfiguracaoJDBC;
import com.dh.clinica.model.Dentista;
import com.dh.clinica.service.IDao;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class DentistaServiceImpl implements IDao<Dentista> {

    private ConfiguracaoJDBC configuracaoJDBC;

    public DentistaServiceImpl() {
        this.configuracaoJDBC = new ConfiguracaoJDBC();
    }

    final static Logger log = Logger.getLogger(DentistaServiceImpl.class);

    @Override
    public Dentista salvar(Dentista dentista) {
        log.debug("Registrando novo dentista: " + dentista.toString());
        Connection connection = configuracaoJDBC.conectarComBancoDeDados();
        Statement stmt = null; String query = String.format("INSERT INTO DENTISTA (NOME, SOBRENOME, MATRICULA) VALUES ('%s', '%s', '%s')", dentista.getNome(), dentista.getSobrenome(), dentista.getMatricula());
        try {
            stmt = connection.createStatement();
            stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next())
                dentista.setId(keys.getInt(1));
                stmt.close();
                connection.close(); }
        catch (SQLException throwables) {
            throwables.printStackTrace(); }
        return dentista;
    }

    @Override
    public List<Dentista> buscarTodos() {
        log.debug("Buscando todos os dentistas");
        Connection connection = configuracaoJDBC.conectarComBancoDeDados();
        Statement stmt = null;
        String query = "SELECT * FROM DENTISTA"; List<Dentista> dentistas = new ArrayList<>();
        try {
            stmt = connection.createStatement();
            ResultSet result = stmt.executeQuery(query);
            while (result.next()) {
                dentistas.add(criarDentista(result)); }
            stmt.close(); connection.close(); }
        catch (SQLException throwables) {
            throwables.printStackTrace(); }
        return dentistas;
    }

    // MÉTODO IMPLEMENTADO DA INTERFACE "IDAO" DE BUSCAR POR ID
    @Override
    public Optional<Dentista> buscarPorId(Integer id) {
        log.debug("Buscando dentista com id: " + id);
        Connection connection = configuracaoJDBC.conectarComBancoDeDados();
        Statement statement = null;
        String query = String.format("SELECT * FROM dentista WHERE id = '%s'", id);
        Dentista dentista = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                dentista = criarDentista(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dentista != null ? Optional.of(dentista) : Optional.empty();
    }

    @Override
    public Dentista atualizar (Dentista dentista) {
        log.debug("Atualizando um Dentista: " + dentista.toString());
        Connection connection = configuracaoJDBC.conectarComBancoDeDados();
        Statement stmt = null;
        String query = String.format("UPDATE DENTISTA SET NOME = '%s', SOBRENOME = '%s', MATRICULA = '%s' WHERE ID = '%s'",
                dentista.getNome(), dentista.getSobrenome(), dentista.getMatricula(), dentista.getId());
        try {
            stmt = connection.createStatement();
            stmt.executeUpdate(query);
            stmt.close();
            connection.close(); }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
            return dentista; }



    @Override
    public void excluir(Integer id) {
        log.debug("Excluinudo dentista com id : " + id);
        Connection connection = configuracaoJDBC.conectarComBancoDeDados();
        Statement stmt = null;
        String query = String.format("DELETE FROM DENTISTA WHERE ID = %s", id);
        try {
            stmt = connection.createStatement();
            stmt.executeUpdate(query);
            stmt.close();
            connection.close(); }
        catch (SQLException throwables) {
            throwables.printStackTrace(); }
    }

    private Dentista criarDentista(ResultSet resultSet) throws SQLException {
        return new Dentista(resultSet.getInt("ID"),
                resultSet.getString("NOME"),
                resultSet.getString("SOBRENOME"),
                resultSet.getString("MATRICULA")); }

}
