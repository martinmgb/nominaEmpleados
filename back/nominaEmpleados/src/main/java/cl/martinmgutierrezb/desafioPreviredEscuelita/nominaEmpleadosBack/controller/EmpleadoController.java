package cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpleadosBack.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpleadosBack.controller.response.RespuestaApi;
import cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpleadosBack.exceptions.BusinessException;
import cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpleadosBack.exceptions.DBException;
import cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpleadosBack.exceptions.ParametroInvalidoException;
import cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpleadosBack.model.Empleado;
import cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpleadosBack.service.EmpleadoService;
import cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpleadosBack.service.EmpleadoServiceImpl;
import cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpleadosBack.util.ResponseUtil;

/**
 * Clase Servlet para el manejo de las peticiones respecto a la entidad Empleado
 * Se disponibilizan metodos para obtener empleados, agregar empleados y para eliminar empleado.
 * 
 * @author martinmgutierrezb
 */
@WebServlet("/api/empleados")
public class EmpleadoController extends HttpServlet {

	
	private static final long serialVersionUID = 1L;
	private EmpleadoService empleadoService = new EmpleadoServiceImpl();

	/**
	 * Metodo para manejar la solicitud de obetener empleados
	 * @return respuesta con los empleados registrados
	 * 
	 */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	RespuestaApi<List<Empleado>> respuesta;
    	List<Empleado> empleados = null;
        
        try {
        	empleados = empleadoService.obtenerEmpleados();
        
	        respuesta = new RespuestaApi<List<Empleado>>(
	                true,
	                "Empleados encontrados",
	                HttpServletResponse.SC_OK,
	                empleados
	            );
        } catch (DBException e) {
        	respuesta = new RespuestaApi<>(
                    false,
                    e.getMessage(),
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR
                );
        	response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}

        ResponseUtil.responder(respuesta, response);
        
        
    }

    /**
     * Metodo para el manejo de la peticion de agregar empleado
     * @param Json con estructura de un empleado
     * @see Empleado.java
     * 
     * @return respuesta con la resolucion de la solicitud
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	RespuestaApi<Empleado> respuesta;

        // Convertir el JSON recibido a un objeto Empleado
        Empleado empleado = (Empleado) ResponseUtil.recibirParametros(request.getReader(), Empleado.class);
        
        try {
            empleadoService.agregarEmpleado(empleado);
        

	        respuesta = new RespuestaApi<Empleado>(
	                true,
	                "Empleado agregado",
	                HttpServletResponse.SC_CREATED,
	                empleado
	            );
	        
	        response.setStatus(HttpServletResponse.SC_CREATED);
        }catch (BusinessException e) {
        	respuesta = new RespuestaApi<>(
                    false,
                    e.getMessage(),
                    HttpServletResponse.SC_BAD_REQUEST
                );
        	response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}catch (DBException e) {
        	respuesta = new RespuestaApi<>(
                    false,
                    e.getMessage(),
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR
                );
        	response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
        
        ResponseUtil.responder(respuesta, response);
    }
    
    
    /**
     * Metodo para el manejo de la peticion de eliminar un empleado
     * @param Id del empleado
     * @return respuesta con la resolucion de la solicitud
     */
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	RespuestaApi<Empleado> respuesta;
    	Boolean eliminado;
    	String idEmpleado = request.getParameter("id");
    	
        
        try {
        	
        	String mensaje;
			if (idEmpleado != null && !idEmpleado.isEmpty()) {
        		eliminado = empleadoService.eliminarEmpleado(Long.valueOf(idEmpleado));
        		if(eliminado) {
        			mensaje = "Empleado eliminado.";
        		} else {
        			mensaje = "No se encontró registro.";
        		}
        	} else {
        		throw new ParametroInvalidoException("Parámetro no puede ser nulo ni vacío.");
        	}
        

	        respuesta = new RespuestaApi<Empleado>(
	                true,
	                mensaje,
	                HttpServletResponse.SC_OK,
	                null
	            );
        }catch (ParametroInvalidoException e) {
        	respuesta = new RespuestaApi<>(
                    false,
                    e.getMessage(),
                    HttpServletResponse.SC_BAD_REQUEST
                );
        	response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}catch (DBException e) {
        	respuesta = new RespuestaApi<>(
                    false,
                    e.getMessage(),
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR
                );
        	response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
        
        ResponseUtil.responder(respuesta, response);
    }
}
