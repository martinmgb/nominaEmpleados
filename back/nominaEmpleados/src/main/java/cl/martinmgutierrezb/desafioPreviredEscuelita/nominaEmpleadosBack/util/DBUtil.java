package cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpleadosBack.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpleadosBack.config.PropertiesConfig;
import cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpleadosBack.exceptions.DBException;
/**
 * Calse para manejar la conexion de base de datos
 * @author martinmgutierrezb
 *
 */
public class DBUtil {
	/**
	 * Metodo que utiliza los datos de la properties para establecer una conexion a la bd
	 * @return La conexion de bd para ser usada
	 * @throws DBException
	 */
    public static Connection getConnection() throws DBException {
    	try {
			Class.forName("org.postgresql.Driver");
		
	    	String url = PropertiesConfig.getDBUrl();
	        String user = PropertiesConfig.getDBUser();
	        String password = PropertiesConfig.getDBPassword();
	        return DriverManager.getConnection(url, user, password);
    	} catch (ClassNotFoundException e) {
			throw new DBException("No se pudo encontrar driver de postgresql: "+e.getMessage());
		} catch (SQLException e) {
			throw new DBException("Error al conectar a la base de datos: "+e.getMessage());
		}
    }
}
