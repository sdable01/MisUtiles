package com.bestcode.misutiles;

import java.sql.Connection;
import java.sql.SQLException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.jdbc.Work;




/**
 *
 * @author jimmy
 */
public class Conexion {

    public static Connection getConexion(SessionFactory sessionFactory) {

        // Obtener el proveedor de conexiones
        SessionFactoryImplementor sessionFactoryImplementor = (SessionFactoryImplementor) sessionFactory;
        //MultiTenantConnectionProvider connectionProvider = sessionFactoryImplementor.getServiceRegistry()
          //      .getService(MultiTenantConnectionProvider.class);
        ConnectionProvider connectionProvider = sessionFactoryImplementor.getServiceRegistry()
                .getService(ConnectionProvider.class);
        
        System.out.println("sessionFactoryImplementor.getServiceRegistry() = " + sessionFactoryImplementor.getServiceRegistry());
        System.out.println("connectionProvider = " + connectionProvider);

        try {
            // Obtener una conexión
            Connection connection = connectionProvider.getConnection();
            return connection;
        } catch (SQLException e) {
            // Manejar la excepción
        }

        return null;
    }
}
