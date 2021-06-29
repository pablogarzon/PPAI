package com.dsigrupo12.ppai.entities;

public enum TipoExposicion {
	
	PERMANENTE("Permanente", "Exposición permanente"), TEMPORAL("Temporal", "Exposición Temporal");
	
	private String nombre;
	
	private String descripcion;

	TipoExposicion(String nombre, String descripcion) {
		this.nombre = nombre;
		this.descripcion = descripcion;
	}
	
	public static TipoExposicion getByName(String val) {
		for (TipoExposicion e : TipoExposicion.values()) {
			if (e.nombre.equals(val))
				return e;
		}
		return null;
	}
}
