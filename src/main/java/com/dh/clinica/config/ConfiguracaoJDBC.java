package com.dh.clinica.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfiguracaoJDBC {

    // Atributos
    private String jdbcDriver;
    private String dbUrl;
    private String nomeUsuario;
    private String senha;

    // CONSTRUTOR COM AS INFORMAÇÕES DE LOGIN JA REGISTRADA PARA ACESSO DIRETO NO BANCO H2
    public ConfiguracaoJDBC() {
        this.jdbcDriver = "org.h2.Driver";
        this.dbUrl = "jdbc:h2:~/clinica;DB_CLOSE_DELAY=-1;INIT=RUNSCRIPT FROM 'create.sql'";
        this.nomeUsuario = "sa";
        this.senha = "";
    }

    //  MÉTODO QUE REALIZA A CONEXÃO NO BANCO DE DADOS
    public Connection conectarComBancoDeDados() {

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(dbUrl, nomeUsuario, senha);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
