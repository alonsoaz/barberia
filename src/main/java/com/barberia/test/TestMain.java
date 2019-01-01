package com.barberia.test;

import com.barberia.entity.CambiarClave;
import com.barberia.entity.InsertarCliente;
import com.barberia.entity.InsertarUsuario;
import com.barberia.entity.LoginCliente;
import com.barberia.entity.LoginUsuario;
import com.barberia.entity.RecuperarBarberoInterno;
import com.barberia.entity.RecuperarClienteInterno;
import com.barberia.entity.RecuperaraUsuario;
import com.barberia.model.*;

public class TestMain {

	public static void main(String[] args) {

	RecuperarBarberoInterno ins = new RecuperarBarberoInterno(1,"alonso","azaldegui","932819009","23456789","alonso_az@hotmail.com","Av. Estados Unidos #115, Callao");

		System.out.println(ReservasModel.getInstance().getReservas(1).get(0).getgInicio());
		
	}

}
