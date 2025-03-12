package cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpleadosBack.service;

import java.util.List;

import cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpleadosBack.model.Empleado;

public interface EmpleadoService {

	List<Empleado> obtenerEmpleados();

	void agregarEmpleado(Empleado empleado);

	Boolean eliminarEmpleado(Long id);
	
	
}
