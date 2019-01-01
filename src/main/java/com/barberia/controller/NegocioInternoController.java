package com.barberia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.barberia.entity.*;
import com.barberia.model.NegocioInternoModel;

@Controller    // This means that this class is a Controller
@RequestMapping(path="/barberia") // This means URL's start with /demo (after Application path)
public class NegocioInternoController {
	
	@Autowired
	private NegocioInternoModel NegocioInternoModel;

	public NegocioInternoController(NegocioInternoModel NegocioInternoModel) {
		this.NegocioInternoModel = NegocioInternoModel;
	}
	
	@GetMapping(value="/{idUser}/tienda/venta/allventas", produces = { "application/json" })
	@ResponseBody
	public List<MostrarVentaInterno> getVenta(
			@PathVariable int idUser
			) {
		return NegocioInternoModel.getInstance().getVenta();
	}
	
	@GetMapping(value="/{idUser}/tienda/venta/{idVenta}", produces = { "application/json" })
	@ResponseBody
	public List<MostrarDetalleVentaInterno> getDetVenta(
			@PathVariable int idUser,
			@PathVariable int idVenta
			) {
		return NegocioInternoModel.getInstance().getDetVenta(idVenta);
	}

	@GetMapping(value="/{idUser}/tienda/reserva/allreservas/barbero/{idBarbero}", produces = { "application/json" })
	@ResponseBody
	public List<ListarReservasPorBarberoInterno> getResBarbero(
			@PathVariable int idUser,
			@PathVariable int idBarbero
			) {
		return NegocioInternoModel.getInstance().getResBarbero(idBarbero);
	}
	
	@GetMapping(value="/{idUser}/tienda/reserva/allreservas/cliente/{idClient}", produces = { "application/json" })
	@ResponseBody
	public List<ListarReservasPorClienteInterno> getResCliente(
			@PathVariable int idUser,
			@PathVariable int idClient
			) {
		return NegocioInternoModel.getInstance().getResCliente(idClient);
	}
	
}
