package cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpleadosBack.exceptions;

/**
 * Clase para excepciones de negocio
 */
public class BusinessException extends RuntimeException {

	/**
	 * Declaración del serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor con mensaje de error
	 * @param mensaje
	 */ 
    public BusinessException(String mensaje) {
        super(mensaje);  
    }

    /**
	 * Constructor con mensaje de error y causa (Throwable)
	 * @param mensaje y causa
	 */
    public BusinessException(String mensaje, Throwable causa) {
        super(mensaje, causa); 
    }
    
}
