package com.dsigrupo12.ppai.entities;

public enum TipoVisita {
	
	COMPLETA("Completa", "Visita Completa"), POR_EXPOSICION("Por Exposicion", "Visita Por Exposición");
	
	private String nombre;
	
	private String descripcion;

	TipoVisita(String nombre, String descripcion) {
		this.nombre = nombre;
		this.descripcion = descripcion;
	}
	
	public static TipoVisita getByName(String val) {
		for (TipoVisita e : TipoVisita.values()) {
			if (e.nombre.equals(val))
				return e;
		}
		return null;
	}

	public String getNombre() {
		return nombre;
	}
}
