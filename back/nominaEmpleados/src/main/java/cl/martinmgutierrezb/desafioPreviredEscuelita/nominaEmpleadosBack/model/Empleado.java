package cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpleadosBack.model;

import java.math.BigDecimal;

/**
 * Clase modelo de empleados
 * @author martinmgutierrezb
 *
 */
public class Empleado {
	public static final String CARGADO = "CARGADO";
	public static final String ERROR_VALIDACION_NEGOCIO = "ERROR EN VALIDACION DE NEGOCIO";
	public static final String ERROR_BD = "ERROR EN BASE DE DATOS";

	private Long id;
	
	private String nombre;
	
	private String apellido;
	
	private Rut rut;
	
	private String cargo;
	
	private BigDecimal salarioBase;
	
	private BigDecimal bono;
	
	private BigDecimal descuento;
	
	private String estatus;
	
	private String observacion;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Rut getRut() {
		return rut;
	}

	public void setRut(Rut rut) {
		this.rut = rut;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public BigDecimal getSalarioBase() {
		return salarioBase;
	}

	public void setSalarioBase(BigDecimal salarioBase) {
		this.salarioBase = salarioBase;
	}

	public BigDecimal getBono() {
		return bono;
	}

	public void setBono(BigDecimal bono) {
		this.bono = bono;
	}

	public BigDecimal getDescuento() {
		return descuento;
	}

	public void setDescuento(BigDecimal descuento) {
		this.descuento = descuento;
	}

	public BigDecimal getSalarioTotal() {
		return salarioBase.add(bono).add(descuento.multiply(new BigDecimal(-1)));
	}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
}
