package cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpleadosBack.service;

import java.math.BigDecimal;
import java.util.List;

import cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpleadosBack.config.PropertiesConfig;
import cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpleadosBack.dao.EmpleadoDAO;
import cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpleadosBack.exceptions.BusinessException;
import cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpleadosBack.model.Empleado;
import cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpleadosBack.util.DBUtil;

/**
 * Clase para el manejo de logica de negocio
 * @author martinmgutierrezb
 *
 */
public class EmpleadoServiceImpl implements EmpleadoService {
	
	private EmpleadoDAO empleadoDAO;

	public EmpleadoServiceImpl() {
		this.empleadoDAO = new EmpleadoDAO(DBUtil.getConnection());
	}

	@Override
	public List<Empleado> obtenerEmpleados(){
		return empleadoDAO.obtenerEmpleados();
	}

	/**
	 * Metodo para agregar un empleado
	 * Se realizan una serie de validaciones de negocio
	 * antes de guardar en la base de datos
	 * @param empleado
	 * @throws BusinessException
	 */
	@Override
	public void agregarEmpleado(Empleado empleado) {
		if(empleado.getSalarioBase().compareTo(PropertiesConfig.getSalarioBaseMinimo())<0) {
			throw new BusinessException("El salario base no puede ser menor a $"+PropertiesConfig.getSalarioBaseMinimo());
		}
		if(empleado.getBono().compareTo(
				empleado.getSalarioBase()
				.multiply(PropertiesConfig.getFraccionMaximaBonoSalarioBase()))>0) {
			throw new BusinessException("Los bonos no pueden ser mayor al "+PropertiesConfig.getFraccionMaximaBonoSalarioBase().multiply(new BigDecimal(100))+"% del salario base.");
		}
		if(empleado.getDescuento().compareTo(empleado.getSalarioBase())>0) {
			throw new BusinessException("Los descuentos no puede ser mayor al salario base.");
		}
		Boolean rutValido = empleadoDAO.obtenerEmpleado(empleado.getRut().getNumero());
		if(!rutValido) {
			throw new BusinessException("Rut ya existe.");
		}
		empleadoDAO.agregarEmpleado(empleado);
	}

	/**
	 * Metodo para eliminar un empleado
	 * @param id del empleado
	 * @return Boleano que indica si logro eliminarse
	 */
	@Override
	public Boolean eliminarEmpleado(Long id) {
		return empleadoDAO.eliminarEmpleado(id);
	}

}
