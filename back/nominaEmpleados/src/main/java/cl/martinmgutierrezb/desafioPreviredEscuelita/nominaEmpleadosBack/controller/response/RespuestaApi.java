package cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpleadosBack.controller.response;

/**
 * Clase para el envio de repuestas de la api
 * @author martinmgutierrezb
 *
 * @param <T> Clase que sera envuelta en la respuesta
 */
public class RespuestaApi<T> {
    private boolean exito;
    private String mensaje;
    private int estatus;
    private T data;

    // Constructor para éxito con datos
    public RespuestaApi(boolean exito, String mensaje, int estatus, T data) {
        this.exito = exito;
        this.mensaje = mensaje;
        this.estatus = estatus;
        this.data = data;
    }

    // Constructor para error con mensaje
    public RespuestaApi(boolean exito, String mensaje, int estatus) {
        this.exito = exito;
        this.mensaje = mensaje;
        this.estatus = estatus;
        this.data = null;
    }

	public boolean isExito() {
		return exito;
	}

	public void setExito(boolean exito) {
		this.exito = exito;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public int getEstatus() {
		return estatus;
	}

	public void setEstatus(int estatus) {
		this.estatus = estatus;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}

