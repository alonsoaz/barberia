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

import com.barberia.model.TipoProductoModel;
import com.barberia.entity.*;

@Controller    // This means that this class is a Controller
@RequestMapping(path="/barberia") // This means URL's start with /demo (after Application path)
public class TipoProductoController {
	
	@Autowired
	private TipoProductoModel TipoProductoModel;

	public TipoProductoController(TipoProductoModel TipoProductoModel) {
		this.TipoProductoModel = TipoProductoModel;
	}

	@GetMapping(value="/{idUser}/marca/{idMarca}/tipo/alltipos", produces = { "application/json" })
	@ResponseBody
	public List<ListarTipoProducto> getTipos(
			@PathVariable int idUser,
			@PathVariable int idMarca			
			) {
		return TipoProductoModel.getInstance().getTipoProductoR();
	}
	
	@GetMapping(value="/{idUser}/marca/{idMarca}/tipo/{id}", produces = { "application/json" })
	@ResponseBody
	public List<RecuperarTipoProducto> getTipoById(
			@PathVariable int idUser,
			@PathVariable int idMarca,
			@PathVariable int id) {
		return TipoProductoModel.getInstance().getTipoProducto(id);
	}
	
	@PostMapping(value="/{idUser}/marca/{idMarca}/tipo/register", produces = { "application/json" }, consumes = { "application/json" })
	@ResponseBody
	public List<MensajesBeans> registerTipo(
					@PathVariable int idUser,
					@PathVariable int idMarca,
					@RequestBody String ins) 
	{
		
		return TipoProductoModel.getInstance().addTipoProducto(ins, idUser);
		
	}
	
	@PutMapping(value="/{idUser}/marca/{idMarca}/tipo/{id}/update", produces = { "application/json" }, consumes = { "application/json" })
	@ResponseBody
	public List<MensajesBeans> updateTipo(
			@PathVariable int idUser,
			@PathVariable int idMarca,
			@PathVariable int id,		
			@RequestBody RecuperarTipoProducto ins
									) {
		
		RecuperarTipoProducto inst = new RecuperarTipoProducto
				
				(
				ins.getbTipo(),
				ins.getCidEstado()
				);
		
		return TipoProductoModel.getInstance().updtTipoProducto(id, inst);
		
	}
	
	@DeleteMapping(value="/{idUser}/marca/{idMarca}/tipo/{id}/delete" , produces = { "application/json" }, consumes = { "application/json" })
	@ResponseBody
	public List<MensajesBeans> deleteServicio(
				@PathVariable int idUser,
				@PathVariable int idMarca,
				@PathVariable int id
									) {
		
		return TipoProductoModel.getInstance().delTipoProducto(id);
		
	}

}
