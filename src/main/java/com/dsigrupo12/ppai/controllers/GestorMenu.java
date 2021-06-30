package com.dsigrupo12.ppai.controllers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dsigrupo12.ppai.entities.Cargo;
import com.dsigrupo12.ppai.entities.Empleado;
import com.dsigrupo12.ppai.entities.Escuela;
import com.dsigrupo12.ppai.entities.Exposicion;
import com.dsigrupo12.ppai.entities.PublicoDestino;
import com.dsigrupo12.ppai.entities.Sede;
import com.dsigrupo12.ppai.entities.TipoExposicion;
import com.dsigrupo12.ppai.repositories.CargoRepository;
import com.dsigrupo12.ppai.repositories.EmpleadoRepository;
import com.dsigrupo12.ppai.repositories.EscuelaRepository;
import com.dsigrupo12.ppai.repositories.ExposicionRepository;
import com.dsigrupo12.ppai.repositories.PublicoDestinoRepository;
import com.dsigrupo12.ppai.repositories.SedeRepository;

@Controller
@RequestMapping("/menu")
public class GestorMenu {
	
	PublicoDestinoRepository pdr;
	SedeRepository sr;
	ExposicionRepository er;
	EmpleadoRepository emr;
	CargoRepository cr;
	EscuelaRepository esr;
	
	@Autowired
	public GestorMenu(PublicoDestinoRepository pdr, SedeRepository sr, ExposicionRepository er,
			EmpleadoRepository emr, CargoRepository cr, EscuelaRepository esr) {
		this.pdr = pdr;
		this.sr = sr;
		this.er = er;
		this.emr = emr;
		this.cr = cr;
		this.esr = esr;
	}
	
	
	@GetMapping
	public @ResponseBody String cargarDatos() {
		
		Escuela escuela = new Escuela();
		escuela.setNombre("IPET 80");
		esr.save(escuela);
		
		PublicoDestino publicoDestino = new PublicoDestino();
		publicoDestino.setNombre("público general");
		publicoDestino = pdr.save(publicoDestino);
		
		
		Exposicion exposicion = new Exposicion();
		exposicion.setNombre("Fotografías WW2");
		exposicion.setHoraApertura(LocalTime.now());
		exposicion.setHoraCierre(LocalTime.now());
		exposicion.setFechaInicio(LocalDate.now().minusDays(10));
		exposicion.setFechaFin(LocalDate.now().plusDays(10));
		exposicion.setPublicoDestino(publicoDestino);
		exposicion.setTipoExposicion(TipoExposicion.TEMPORAL);
		exposicion = er.save(exposicion);
		
		
		Sede seleccionada = new Sede();
		seleccionada.setExposiciones(Collections.singletonList(exposicion));
		seleccionada.setNombre("Museo Caraffa");
		seleccionada.setCantMaximaVisitantes(10);
		seleccionada.setCantMaxPorGuia(5);
		
		Cargo guia = cr.save(new Cargo("Guia"));
		Cargo rv = cr.save(new Cargo("RV"));
		
		List<Empleado> empleados = new ArrayList<Empleado>();

		empleados.add(new Empleado(30510111, "Arango", "Jose", guia));
		empleados.add(new Empleado(30510411, "Paez", "Maria", guia));
		empleados.add(new Empleado(31514611, "Borghese", "Claudia", guia));
		empleados.add(new Empleado(32510411, "Schmit", "Miguel", guia));
		empleados.add(new Empleado(36510411, "Schmit", "Roberto", guia));
		empleados.add(new Empleado(36510432, "Gonzales", "Carlos", guia));
		empleados.add(new Empleado(37510432, "Panini", "Ricardo", rv));
		
		seleccionada.setEmpleados(empleados);
		sr.save(seleccionada);
		
		return "datos cargados";
	}
}
