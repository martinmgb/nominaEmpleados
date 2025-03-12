package cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpleadosBack.config;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Properties;

/**
 * Clase para configurar properties de la api.
 * Propiedades como datos de conexion que dependen de variables de entorno
 * y propiedades de reglas de negocio
 *
 */
public class PropertiesConfig {

    private static Properties properties = new Properties();

    static {
        try (InputStream inputStream = PropertiesConfig.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (inputStream != null) {
                properties.load(inputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Metodo para obtener la url del conexion a la bd
     * Para ser utilizada deben estar las variables de entorno disponibles
     */
    public static String getDBUrl() {
        return properties.getProperty("db.url")
        		.replace("${DB_HOST}", System.getenv("DB_HOST"))
                .replace("${DB_PORT}", System.getenv("DB_PORT"))
                .replace("${DB_DATABASE}", System.getenv("DB_DATABASE"));
    }
    
    /**
     * Metodo para obtener el usuario de bd
     * Para ser utilizada deben estar las variables de entorno disponibles
     */
    public static String getDBUser() {
        return properties.getProperty("db.user")
        		.replace("${DB_USER}", System.getenv("DB_USER"));
    }
    
    /**
     * Metodo para obtener el password de bd
     * Para ser utilizada deben estar las variables de entorno disponibles
     */
    public static String getDBPassword() {
        return properties.getProperty("db.password")
        		.replace("${DB_PASSWORD}", System.getenv("DB_PASSWORD"));
    }
    
    /**
     * Metodo para obtener el salario minimo que debe tener el registro de un empleado
     * 
     */
    public static BigDecimal getSalarioBaseMinimo() {
        return new BigDecimal(properties.getProperty("business.regla.salarioBaseMinimo"));
    }
    
    /**
     * Metodo para obtener la fraccion maxima de bono con respecto al salario base
     * 
     */
    public static BigDecimal getFraccionMaximaBonoSalarioBase() {
        return new BigDecimal(properties.getProperty("business.regla.fraccionMaximaBonoSalarioBase"));
    }
}

