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

import com.barberia.model.ProductoModel;
import com.barberia.entity.*;

@Controller    // This means that this class is a Controller
@RequestMapping(path="/barberia") // This means URL's start with /demo (after Application path)
public class ProductoController {
	
	@Autowired
	private ProductoModel ProductoModel;

	public ProductoController(ProductoModel ProductoModel) {
		this.ProductoModel = ProductoModel;
	}

	@GetMapping(value="/{idUser}/producto/allproductos", produces = { "application/json" })
	@ResponseBody
	public List<ListarProductoInterno> getProductos(
			@PathVariable int idUser
			) {
		return ProductoModel.getInstance().getProductoRecords();
	}
	
	@GetMapping(value="/{idUser}/producto/allproductos", params = "words", produces = { "application/json" })
	@ResponseBody
	public List<BuscaProductoInterno> getProductoByWords(
			@PathVariable int idUser,
			@RequestParam String words) {
		return ProductoModel.getInstance().getProductoByWords(words);
	}
	
	@GetMapping(value="/{idClient}/android/producto/allproductos", produces = { "application/json" })
	@ResponseBody
	public List<ListarProducto> getServ(
			@PathVariable int idClient
			) {
		return ProductoModel.getInstance().getProductoR();
	}
	
	@GetMapping(value="/{idClient}/android/producto/allproductos", params = "words", produces = { "application/json" })
	@ResponseBody
	public List<BuscarProducto> getServByWords(
			@PathVariable int idClient,			
			@RequestParam String words
			) {
		return ProductoModel.getInstance().getProdByWords(words);
	}
	  
	
	@GetMapping(value="/{idClient}/android/producto/{id}", produces = { "application/json" })
	@ResponseBody
	public List<RecuperarProductoInterno> getServById(
			@PathVariable int idClient,
			@PathVariable int id	
			) {
		return ProductoModel.getInstance().getProd(id);
	}	
	
	@GetMapping(value="/{idUser}/producto/{id}", produces = { "application/json" })
	@ResponseBody
	public List<RecuperarProductoInterno> getServicioById(
			@PathVariable int idUser,			
			@PathVariable int id	
			) {
		return ProductoModel.getInstance().getProducto(id);
	}
	
	@PostMapping(value="/{idUser}/marca/{idMarca}/tipo/{idTipo}/producto/register", produces = { "application/json" }, consumes = { "application/json" })
	@ResponseBody
	public List<MensajesBeans> registerProducto(
					@PathVariable int idUser,
					@PathVariable int idMarca,
					@PathVariable int idTipo,	
					@RequestBody InsertarProducto ins) 
	{
		
		InsertarProducto inst = new InsertarProducto
				(
				ins.getAnombre(), 
				ins.getBidMarca(), 
				ins.getCidTipo(), 
				ins.getdStock(),
				ins.getePrecio(), 
				ins.getFdescripcion()
				);
		
		return ProductoModel.getInstance().addProducto(inst, idUser, idMarca, idTipo);
		
	}
	
	@PutMapping(value="/{idUser}/producto/{id}/update", produces = { "application/json" }, consumes = { "application/json" })
	@ResponseBody
	public List<MensajesBeans> updateProducto(
			@PathVariable int idUser,
			@PathVariable int id,		
			@RequestBody RecuperarProductoInterno ins
									) {
		
		RecuperarProductoInterno inst = new RecuperarProductoInterno
				
				(
				ins.getaIdMarca(),
				ins.getBidTipo(),
				ins.getcNombre(),
				ins.getDidEstado(),
				ins.getfStock(), 
				ins.getgPrecio(), 
				ins.getHdescripcion()
				);
		
		return ProductoModel.getInstance().updtProducto(id, inst, idUser);
		
	}
	
	@DeleteMapping(value="/{idUser}/producto/{id}/delete" , produces = { "application/json" }, consumes = { "application/json" })
	@ResponseBody
	public List<MensajesBeans> deleteProducto(
			@PathVariable int idUser,		
			@PathVariable int id	
									) {
		
		return ProductoModel.getInstance().delProducto(id);
		
	}
	
}
