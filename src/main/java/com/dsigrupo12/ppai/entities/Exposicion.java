package com.dsigrupo12.ppai.entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "exposiciones")
public class Exposicion {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "exposicion_id")
	private int id;

	private String nombre;
	
	private LocalDate fechaInicio;
	
	private LocalDate fechaFin;
	
	private LocalTime horaApertura;
	
	private LocalTime horaCierre;

	@OneToOne
	@JoinColumn(name = "publico_destino_id")
	private PublicoDestino publicoDestino;

	@Enumerated(EnumType.STRING)
	private TipoExposicion tipoExposicion;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "detalle_exposicion_id")
	private List<DetalleExposicion> detallesExposicion;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public LocalDate getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}

	public LocalTime getHoraApertura() {
		return horaApertura;
	}

	public void setHoraApertura(LocalTime horaApertura) {
		this.horaApertura = horaApertura;
	}

	public LocalTime getHoraCierre() {
		return horaCierre;
	}

	public void setHoraCierre(LocalTime horaCierre) {
		this.horaCierre = horaCierre;
	}
	
	public PublicoDestino getPublicoDestino() {
		return publicoDestino;
	}

	public void setPublicoDestino(PublicoDestino publicoDestino) {
		this.publicoDestino = publicoDestino;
	}

	public TipoExposicion getTipoExposicion() {
		return tipoExposicion;
	}

	public void setTipoExposicion(TipoExposicion tipoExposicion) {
		this.tipoExposicion = tipoExposicion;
	}

	public List<DetalleExposicion> getDetallesExposicion() {
		return detallesExposicion;
	}

	public void setDetallesExposicion(List<DetalleExposicion> detallesExposicion) {
		this.detallesExposicion = detallesExposicion;
	}

	public long calcularDuracionEstimada() {
		long total = 0L;
		for (DetalleExposicion detalleExposicion : detallesExposicion) {
			total += detalleExposicion.buscarDuracionExtObras();
		}
		return total;
	}

}
