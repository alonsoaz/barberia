package com.barberia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.barberia.model.ServicioModel;
import com.barberia.entity.*;

@Controller    // This means that this class is a Controller
@RequestMapping(path="/barberia") // This means URL's start with /demo (after Application path)
public class ServicioController {
	
	@Autowired
	private ServicioModel ServicioModel;

	public ServicioController(ServicioModel ServicioModel) {
		this.ServicioModel = ServicioModel;
	}

	@GetMapping(value="/{idUser}/servicio/allservicios", produces = { "application/json" })
	@ResponseBody
	public List<ListarServicioInterno> getServicios(
			@PathVariable int idUser
			) {
		return ServicioModel.getInstance().getServicioRecords();
	}
	
	@GetMapping(value="/{idUser}/servicio/allservicios", params = "words", produces = { "application/json" })
	@ResponseBody
	public List<BuscarServicioInterno> getServicioByWords(
			@PathVariable int idUser, 
			@RequestParam String words
			) {
		return ServicioModel.getInstance().getServicioByWords(words);
	}
	
	@GetMapping(value="/{idCient}/android/servicio/allservicios", produces = { "application/json" })
	@ResponseBody
	public List<ListarServicio> getServ(
			@PathVariable int idClient
			) {
		return ServicioModel.getInstance().getServicioR();
	}
	
	@GetMapping(value="/{idClient}/android/servicio/allservicios", params = "words", produces = { "application/json" })
	@ResponseBody
	public List<BuscarServicio> getServByWords(
			@PathVariable int idClient,
			@RequestParam String words) {
		return ServicioModel.getInstance().getServByWords(words);
	}
	
	@GetMapping(value="/{idClient}/android/servicio/{id}", produces = { "application/json" })
	@ResponseBody
	public List<RecuperarServicioInterno> getServById(
			@PathVariable int idClient, 
			@PathVariable int id
			) {
		return ServicioModel.getInstance().getServicio(id);	
	}
	
	@GetMapping(value="/{idUser}/servicio/{id}", produces = { "application/json" })
	@ResponseBody
	public List<RecuperarServicioInterno> getServicioById(
			@PathVariable int idUser, 
			@PathVariable int id
			) {
		return ServicioModel.getInstance().getServicio(id);
	}
	
	@PostMapping(value="/{idUser}/servicio/register", produces = { "application/json" }, consumes = { "application/json" })
	@ResponseBody
	public List<MensajesBeans> registerServicio(
					@PathVariable int idUser,
					@RequestBody InsertarServicio ins
					) 
	{
		
		InsertarServicio inst = new InsertarServicio
				(
				ins.getaNombre(), 
				ins.getbDuracion(), 
				ins.getcPrecio(), 
				ins.getEdescripcion()
				);
		
		return ServicioModel.getInstance().addServicio(inst, idUser);
		
	}
	
	@PutMapping(value="/{idUser}/servicio/{id}/update", produces = { "application/json" }, consumes = { "application/json" })
	@ResponseBody
	public List<MensajesBeans> updateServicio(
			@PathVariable int idUser,
			@PathVariable int id,		
			@RequestBody RecuperarServicioInterno ins
									) {
		
		RecuperarServicioInterno inst = new RecuperarServicioInterno
				
				(
				ins.getBnombser(),
				ins.getCidEstado(),
				ins.getDminutos(),
				ins.getEprecio(),
				ins.getFdescripcion()
				);
		
		return ServicioModel.getInstance().updtServicio(id, inst, idUser);
		
	}
	
	@DeleteMapping(value="/{idUser}/servicio/{id}/delete" , produces = { "application/json" }, consumes = { "application/json" })
	@ResponseBody
	public List<MensajesBeans> deleteServicio(
				@PathVariable int idUser,
				@PathVariable int id
									) {
		
		return ServicioModel.getInstance().delServicio(id);
		
	}
	
}
