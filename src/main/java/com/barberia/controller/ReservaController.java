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
import org.springframework.web.bind.annotation.ResponseBody;

import com.barberia.entity.*;
import com.barberia.model.ReservasModel;

@Controller    // This means that this class is a Controller
@RequestMapping(path="/barberia") // This means URL's start with /demo (after Application path)
public class ReservaController {
	
	@Autowired
	private ReservasModel ReservasModel;

	public ReservaController(ReservasModel ReservasModel) {
		this.ReservasModel = ReservasModel;
	}
	
	@PostMapping(value="/{idClient}/android/barbero/{idBarbero}/consulta/{idServicio}", produces = { "application/json" }, consumes = { "application/json" })
	@ResponseBody
	public List<MensajesBeans> askBarbero(
			@RequestBody ConsultaDisponibilidadBarbero ins,
			@PathVariable int idBarbero,
			@PathVariable int idClient,
			@PathVariable int idServicio
			) {
		
		ConsultaDisponibilidadBarbero inst = new ConsultaDisponibilidadBarbero(
				ins.getbYear(), 
				ins.getcMonth(), 
				ins.getdDay(), 
				ins.geteHour(), 
				ins.getfMinut()
				);
				
		
		return ReservasModel.getInstance().askBarbero(inst, idBarbero, idServicio);
	}
	
	@PostMapping(value="/{idClient}/android/barbero/{idBarbero}/reserva/{idServicio}", produces = { "application/json" }, consumes = { "application/json" })
	@ResponseBody
	public List<MensajesBeans> tryBarbero(
			@RequestBody ConsultaDisponibilidadBarbero ins,
			@PathVariable int idBarbero,
			@PathVariable int idClient,
			@PathVariable int idServicio
			) {
		
		ConsultaDisponibilidadBarbero inst = new ConsultaDisponibilidadBarbero(
				ins.getbYear(), 
				ins.getcMonth(), 
				ins.getdDay(), 
				ins.geteHour(), 
				ins.getfMinut()
				);
				
		
		return ReservasModel.getInstance().tryBarbero(inst, idBarbero, idClient, idServicio);
	}
	
	@GetMapping(value="/{idClient}/android/reservas/allreservas", produces = { "application/json" })
	@ResponseBody
	public List<ListarReservasPorCliente> getReservas(
			@PathVariable int idClient
			) {
		return ReservasModel.getInstance().getReservas(idClient);
	}
	
	@GetMapping(value="/{idClient}/android/reservas/{idReserva}", produces = { "application/json" })
	@ResponseBody
	public List<BuscarReservaPorCliente> getReserva(
			@PathVariable int idClient,
			@PathVariable int idReserva
			) {
		return ReservasModel.getInstance().getReserva(idClient,idReserva);
	}
	
	@DeleteMapping(value="/{idClient}/android/reservas/{idReserva}/delete" , produces = { "application/json" }, consumes = { "application/json" })
	@ResponseBody
	public List<MensajesBeans> delReserva(
			@PathVariable int idClient,		
			@PathVariable int idReserva	
									) {
		
		return ReservasModel.getInstance().delReserva(idClient, idReserva);
		
	}
	
	@PutMapping(value="/{idClient}/android/reservas/{idReserva}/pay" , produces = { "application/json" }, consumes = { "application/json" })
	@ResponseBody
	public List<MensajesBeans> payReserva(
			@PathVariable int idClient,		
			@PathVariable int idReserva	
									) {
		
		return ReservasModel.getInstance().payReserva(idClient, idReserva);
		
	}
	
	

}
