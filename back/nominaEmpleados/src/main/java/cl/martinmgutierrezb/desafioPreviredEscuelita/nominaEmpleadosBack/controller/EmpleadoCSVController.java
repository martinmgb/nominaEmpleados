package cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpleadosBack.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpleadosBack.controller.response.RespuestaApi;
import cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpleadosBack.exceptions.BusinessException;
import cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpleadosBack.exceptions.DBException;
import cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpleadosBack.model.Empleado;
import cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpleadosBack.service.EmpleadoService;
import cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpleadosBack.service.EmpleadoServiceImpl;
import cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpleadosBack.util.ConversorUtil;
import cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpleadosBack.util.ResponseUtil;
/**
 * Clase Servlet para el manejo de peticion de carga de empleados a traves de un archivo csv
 * @author martinmgutierrezb
 *
 */
@WebServlet("/api/nominas/calcular")
public class EmpleadoCSVController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private EmpleadoService empleadoService = new EmpleadoServiceImpl();
	
	/**
	 * Metodo para la carga de archivo csv
	 * Se extrae el archivo que viene de la peticion, de recorre por linea
	 * y por cadauna de ellas se agrega el empleado, pasando por validaciones de negocio
	 * 
	 * @param Archivo csv con los empleados
	 * @return Todos los empleados procesados con el estatus de su carga.
	 */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	RespuestaApi<List<Empleado>> respuesta = null;
    	List<Empleado> empleadosCsv = new ArrayList<Empleado>();
    	
    	// Indicar que el tipo de contenido será JSON
        response.setContentType("application/json");

        // Comprobamos si se trata de una solicitud multipart (subida de archivo)
        if (ServletFileUpload.isMultipartContent(request)) {
            // Configuramos el archivo de subida
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);

            try {
                // Procesamos los elementos de la solicitud multipart
                List<FileItem> items = upload.parseRequest(request);

                // Iteramos sobre los items y buscamos el archivo
                for (FileItem item : items) {
                    if (!item.isFormField()) {
                        // Aquí obtenemos el archivo subido
                        InputStream fileContent = item.getInputStream();

                        // Procesamos el CSV, convertimos a String y separamos por líneas
                        BufferedReader reader = new BufferedReader(new InputStreamReader(fileContent));
                        String line;
                        boolean cabecera = true;
                        while ((line = reader.readLine()) != null) {
                            String[] columns = line.split(",");  // Asumimos que las columnas están separadas por comas
                            if(cabecera) {
                            	cabecera=false;
                            	continue;
                            }
                            Empleado empleado = ConversorUtil.convertir(columns);
                            try {
                            	empleadoService.agregarEmpleado(empleado);
                            	empleado.setEstatus(Empleado.CARGADO);
                            }catch (BusinessException e) {
                            	empleado.setEstatus(Empleado.ERROR_VALIDACION_NEGOCIO);
                            	empleado.setObservacion(e.getMessage());
							}catch (DBException e) {
                            	empleado.setEstatus(Empleado.ERROR_BD);
                            	empleado.setObservacion(e.getMessage());
							}
                            empleadosCsv.add(empleado);
                        }
                        respuesta = new RespuestaApi<List<Empleado>>(
            	                true,
            	                "Archivo CSV procesado",
            	                HttpServletResponse.SC_OK,
            	                empleadosCsv
            	            );
                        reader.close();
                    }
                }
            } catch (Exception e) {
            	respuesta = new RespuestaApi<>(
                        false,
                        "Error inesperado: "+e.getMessage(),
                        HttpServletResponse.SC_INTERNAL_SERVER_ERROR
                    );
            	response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } else {
        	respuesta = new RespuestaApi<>(
                    false,
                    "No hay contenido multipart en la solicitud",
                    HttpServletResponse.SC_BAD_REQUEST
                );
        	response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        ResponseUtil.responder(respuesta, response);
    }
}
