package com.pineapple.intime.model;

public class Incidencia {
	private String email;
	private String estado;
	private String asunto;
	private String descripcion;
	private String tipo;
	private String fecha;
	
	public Incidencia(String email, String estado, String asunto, String descripcion, String tipo, String fecha) {
		super();
		this.email = email;
		this.estado = estado;
		this.asunto = asunto;
		this.descripcion = descripcion;
		this.tipo = tipo;
		this.fecha = fecha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}


}
