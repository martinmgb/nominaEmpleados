package cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpleadosBack.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpleadosBack.exceptions.DBException;
import cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpleadosBack.model.Empleado;
import cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpleadosBack.model.Rut;
/**
 * Clase para el manejo de acceso a datos de bd de los empleados
 * @author martinmgutierrezb
 *
 */
public class EmpleadoDAO {
	
	private Connection connection;

	public EmpleadoDAO(Connection connection) {
		super();
		this.connection = connection;
	}
	
	/**
	 * Metodo para la extraccion de los empleados registrados en bd
	 * @return Lista de empleados registrados
	 * @throws DBException
	 */
	public List<Empleado> obtenerEmpleados() {
        List<Empleado> empleados = new ArrayList<>();
        String query = "SELECT * FROM empleados";

        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Long id = rs.getLong("id");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                Long numeroRut = rs.getLong("numero_rut");
                String digitoVerificador = rs.getString("digito_verificador");
                String cargo = rs.getString("cargo");
                BigDecimal salarioBase = new BigDecimal(rs.getString("salario_base"));
                BigDecimal bono = new BigDecimal(rs.getString("bono"));
                BigDecimal descuento = new BigDecimal(rs.getString("descuento"));
                
                Empleado empleado = new Empleado();
                empleado.setId(id);
                empleado.setNombre(nombre);
                empleado.setApellido(apellido);
                empleado.setRut(new Rut(numeroRut,digitoVerificador));
                empleado.setCargo(cargo);
                empleado.setSalarioBase(salarioBase);
                empleado.setBono(bono);
                empleado.setBono(bono);
                empleado.setDescuento(descuento);
                empleados.add(empleado);
            }

        } catch (SQLException e) {
            throw new DBException("Error en base de datos al obtener datos: "+ e.getMessage());
        }

        return empleados;
    }
	
	/**
	 * Metodo para agregar un empleado a la base de datos
	 * @param empleado
	 * @throws DBException
	 */
    public void agregarEmpleado(Empleado empleado) {
        String query = "INSERT INTO empleados (nombre, apellido, numero_rut, digito_verificador, cargo, salario_base, bono, descuento) "
        		+ "		VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, empleado.getNombre());
            stmt.setString(2, empleado.getApellido());
            stmt.setLong(3, empleado.getRut().getNumero());
            stmt.setString(4, empleado.getRut().getDigitoVerificador());
            stmt.setString(5, empleado.getCargo());
            stmt.setBigDecimal(6, empleado.getSalarioBase());
            stmt.setBigDecimal(7, empleado.getBono());
            stmt.setBigDecimal(8, empleado.getDescuento());
            stmt.executeUpdate();

        } catch (SQLException e) {
        	throw new DBException("Error en base de datos al insertar: "+ e.getMessage());
        }
    }
    
    /**
     * Metodo para eliminar un empleado de la bd
     * @param id del empleado
     * @return Un booleano que indica se logro el eliminar el registro
     * 
     * @throws DBException
     */
	public Boolean eliminarEmpleado(Long id) {
		String query = "DELETE FROM empleados WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {

        	// Establecer el valor del parámetro (usuarioId)
        	stmt.setLong(1, id);
            
            // Ejecutar la consulta
            int registrosEliminados = stmt.executeUpdate();
            
            // Si se afectaron filas, el usuario fue eliminado con éxito
            return registrosEliminados > 0;

        } catch (SQLException e) {
        	throw new DBException("Error en base de datos al eliminar: "+ e.getMessage());
        }
	}
	
	/**
	 * Metodo para saber si existe el rut de un empleado en base de datos
	 * @param rut
	 * @return Un booleano que indica si lo encontro
	 * 
	 * @throws DBException
	 */
	public Boolean obtenerEmpleado(Long rut) {
		String query = "SELECT COUNT(*) FROM empleados WHERE numero_rut = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query);) {

        	// Establecer el valor del parámetro (numero de rut)
        	stmt.setLong(1, rut);
        	ResultSet rs = stmt.executeQuery();
        	int numeroDeFilas = 0;
        	if (rs.next()) {
        	    numeroDeFilas = rs.getInt(1); // Obtenemos el número de filas
        	}
            
            return numeroDeFilas == 0;

        } catch (SQLException e) {
        	throw new DBException("Error en base de datos al consultar: "+ e.getMessage());
        }
	}
}
