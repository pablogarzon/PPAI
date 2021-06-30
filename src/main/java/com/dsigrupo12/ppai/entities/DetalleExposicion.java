package com.dsigrupo12.ppai.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class DetalleExposicion {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "detalle_exposicion_id")
    private int id;
	
	private String lugarAsignado;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "obra_id")
	private Obra obra;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLugarAsignado() {
		return lugarAsignado;
	}

	public void setLugarAsignado(String lugarAsignado) {
		this.lugarAsignado = lugarAsignado;
	}
	
	public Obra getObra() {
		return obra;
	}

	public void setObra(Obra obra) {
		this.obra = obra;
	}

	public double buscarDuracionExtObras() {
		return this.obra.getDuracionExtendida();
	}
}
