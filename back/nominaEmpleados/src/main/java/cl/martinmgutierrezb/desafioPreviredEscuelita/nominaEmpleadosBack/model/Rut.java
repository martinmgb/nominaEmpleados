package cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpleadosBack.model;

/**
 * Clase modelo de Rut
 * @author martinmgutierrezb
 *
 */
public class Rut {
	private Long numero;
	
	private String digitoVerificador;

	public Rut() {
		super();
	}

	public Rut(Long numero, String digitoVerificador) {
		super();
		this.numero = numero;
		this.digitoVerificador = digitoVerificador;
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public String getDigitoVerificador() {
		return digitoVerificador;
	}

	public void setDigitoVerificador(String digitoVerificador) {
		this.digitoVerificador = digitoVerificador;
	}
	
	
}
