package com.barberia.entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class InsertarServicio {
	
	private String aNombre;
	private int bDuracion;
	private double cPrecio;
	private String edescripcion;
	public InsertarServicio(String aNombre, int bDuracion, double cPrecio, String edescripcion) {
		super();
		this.aNombre = aNombre;
		this.bDuracion = bDuracion;
		this.cPrecio = cPrecio;
		this.edescripcion = edescripcion;
	}
	public String getaNombre() {
		return aNombre;
	}
	public void setaNombre(String aNombre) {
		this.aNombre = aNombre;
	}
	public int getbDuracion() {
		return bDuracion;
	}
	public void setbDuracion(int bDuracion) {
		this.bDuracion = bDuracion;
	}
	public double getcPrecio() {
		return cPrecio;
	}
	public void setcPrecio(double cPrecio) {
		this.cPrecio = cPrecio;
	}
	public String getEdescripcion() {
		return edescripcion;
	}
	public void setEdescripcion(String edescripcion) {
		this.edescripcion = edescripcion;
	}
	
	


}
