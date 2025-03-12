package cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpleadosBack.util;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpleadosBack.controller.response.RespuestaApi;

/**
 * Clase utilitaria que maneja la recepcion de parametros de una peticion y respuesta de una api
 * @author martinmgutierrezb
 *
 */
public class ResponseUtil {
	/**
	 * Metodo que configura la respuesta de la api
	 * @param respuesta
	 * @param response
	 * @throws IOException
	 */
    public static <T> void responder(RespuestaApi<T> respuesta, HttpServletResponse response) throws IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        // Convertir la lista de usuarios a JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResponse = objectMapper.writeValueAsString(respuesta);

        // Enviar la respuesta
        response.getWriter().write(jsonResponse);
    }
    
    /**
     * Metodo que extrae los parametros del request y los devuelve en el tipo de objeto que
     * esperamos recibir
     * @param readerRequest
     * @param tipoObjeto
     * @return Objeto con parametros del request
     * @throws IOException
     */
    public static <T> Object recibirParametros(BufferedReader readerRequest, Class<T> tipoObjeto) throws IOException {
    	// Crear un ObjectMapper de Jackson para convertir JSON a objeto Java
        ObjectMapper objectMapper = new ObjectMapper();

        // Leer el cuerpo de la solicitud (JSON enviado)
        StringBuilder jsonInput = new StringBuilder();
        String line;
        try (BufferedReader reader = readerRequest) {
            while ((line = reader.readLine()) != null) {
                jsonInput.append(line);
            }
        }

        // Convertir el JSON recibido a un objeto
        return objectMapper.readValue(jsonInput.toString(), tipoObjeto);
    }
}
