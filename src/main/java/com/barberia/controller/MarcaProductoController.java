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

import com.barberia.model.MarcaProductoModel;
import com.barberia.entity.*;

@Controller    // This means that this class is a Controller
@RequestMapping(path="/barberia") // This means URL's start with /demo (after Application path)
public class MarcaProductoController {
	
	@Autowired
	private MarcaProductoModel MarcaProductoModel;

	public MarcaProductoController(MarcaProductoModel MarcaProductoModel) {
		this.MarcaProductoModel = MarcaProductoModel;
	}

	@GetMapping(value="/{idUser}/marca/allmarcas", produces = { "application/json" })
	@ResponseBody
	public List<ListarMarca> getMarcas(@PathVariable int idUser) {
		return MarcaProductoModel.getInstance().getMarcaProductoR();
	}
	
	@GetMapping(value="/{idUser}/marca/{id}", produces = { "application/json" })
	@ResponseBody
	public List<RecuperarMarcaInterno> getMarcaById(@PathVariable int idUser, @PathVariable int id) {
		return MarcaProductoModel.getInstance().getMarcaProducto(id);
	}
	
	@PostMapping(value="/{idUser}/marca/register", produces = { "application/json" }, consumes = { "application/json" })
	@ResponseBody
	public List<MensajesBeans> registerMarca(
					@PathVariable int idUser,
					@RequestBody String ins) 
	{
		
		return MarcaProductoModel.getInstance().addMarcaProducto(ins, idUser);
		
	}
	
	@PutMapping(value="/{idUser}/marca/{id}/update", produces = { "application/json" }, consumes = { "application/json" })
	@ResponseBody
	public List<MensajesBeans> updateMarca(
			@PathVariable int idUser,
			@PathVariable int id,		
			@RequestBody RecuperarMarcaInterno ins
									) {
		
		RecuperarMarcaInterno inst = new RecuperarMarcaInterno
				
				(
				ins.getCmarca(),
				ins.getDidEstado()
				);
		
		return MarcaProductoModel.getInstance().updtMarcaProducto(id, inst);
		
	}
	
	@DeleteMapping(value="/{idUser}/Marca/{id}/delete" , produces = { "application/json" }, consumes = { "application/json" })
	@ResponseBody
	public List<MensajesBeans> deleteServicio(
				@PathVariable int idUser,
				@PathVariable int id
									) {
		
		return MarcaProductoModel.getInstance().delMarcaProducto(id);
		
	}

}
