package cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpleadosBack.util;

import java.math.BigDecimal;

import cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpleadosBack.model.Empleado;
import cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpleadosBack.model.Rut;
/**
 * Clase utilitaria para transformar datos de un objeto
 * a una instancia de otro
 * @author martinmgutierrezb
 *
 */
public class ConversorUtil {

	/**
	 * Metodo que convierte el arreglo de string con los datos en un objeto de tipo Empleado
	 * @param Arreglo de string con el registroCsv
	 * @return Objeto de tipo Empleado
	 * @see Empleado.java
	 */
    public static Empleado convertir(String[] registroCsv) {
		Empleado empleado = new Empleado();
		String[] rutCsv = registroCsv[0].split("-");
		Rut rut = new Rut(Long.valueOf(rutCsv[0]), rutCsv[1]);
		empleado.setRut(rut);
		empleado.setNombre(registroCsv[1]);
		empleado.setApellido(registroCsv[2]);
		empleado.setCargo(registroCsv[3]);
		empleado.setSalarioBase(new BigDecimal(registroCsv[4]));
		empleado.setBono(new BigDecimal(registroCsv[5]));
		empleado.setDescuento(new BigDecimal(registroCsv[6]));
		
		return empleado;
    }
}
