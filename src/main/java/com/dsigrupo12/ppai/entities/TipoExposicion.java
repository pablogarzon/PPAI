package com.dsigrupo12.ppai.entities;

public enum TipoExposicion {
	PERMANENTE("Permanente", "Exposición permanente"), TEMPORAL("Temporal", "Exposición Temporal");
	
	private String descripcion;
	
	private String nombre;

	TipoExposicion(String nombre, String descripcion) {
		this.nombre = nombre;
		this.descripcion = descripcion;
	}	
}
