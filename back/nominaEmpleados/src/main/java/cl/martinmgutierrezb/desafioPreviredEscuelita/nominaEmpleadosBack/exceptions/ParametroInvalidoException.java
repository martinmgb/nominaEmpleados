package cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpleadosBack.exceptions;

/**
 * Clase para excepcion para parametros invalidos
 */
public class ParametroInvalidoException extends RuntimeException {

	/**
	 * Declaración del serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor con mensaje de error
	 * @param mensaje
	 */ 
    public ParametroInvalidoException(String mensaje) {
        super(mensaje);  
    }

    /**
	 * Constructor con mensaje de error y causa (Throwable)
	 * @param mensaje y causa
	 */
    public ParametroInvalidoException(String mensaje, Throwable causa) {
        super(mensaje, causa); 
    }
    
}
