package com.mycompany.asiproyecto.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {
    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;

    static {
        String host;
        String port;
        String database;
        String user;
        String password;
        String url;
        
        //AWS RDS MARIADB
        host = "asidb.cle8qc2ek8bq.us-east-2.rds.amazonaws.com";
        port = "3306";
        database = "asidb";
        user = "admin";
        password = "Aq78ASNkBe0wSPBDd9E7";
        url = "jdbc:mariadb://" + host + ":" + port + "/" + database;
        
        config.setJdbcUrl(url);
        config.setUsername(user);
        config.setPassword(password);

        // --- Connection Pool Configuration ---
        config.setMaximumPoolSize(10);
        config.setMinimumIdle(5);
        config.setIdleTimeout(300000);
        config.setConnectionTimeout(30000);

        // Optimizaciones recomendadas para MariaDB/MySQL
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        // Se crea el DataSource con la configuración
        ds = new HikariDataSource(config);
        System.out.println("¡Pool de conexiones HikariCP inicializado");
        
        // Se asegura que se cierre el ConnectionPool cuando se cierra el programa
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Cerrando ConnectionPool...");
            ConnectionPool.closePool();
        }));
    }

    private ConnectionPool() {}

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
    
    public static void closePool() {
        if (ds != null) {
            ds.close();
            System.out.println("Pool de conexiones HikariCP cerrado.");
        }
    }
}