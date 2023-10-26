package com.ye.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ye.entities.Pedido;
import com.ye.services.PedidoService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

	private final PedidoService PedidoService;

	@Autowired
	public PedidoController(PedidoService PedidoService) {
		this.PedidoService = PedidoService;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Pedido> buscaPedidoControlId(@PathVariable Long id) {
		Pedido Pedido = PedidoService.getPedidoById(id);
		if (Pedido != null) {
			return ResponseEntity.ok(Pedido);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/")
	public ResponseEntity<List<Pedido>> buscaTodasLigacoesControl() {
		List<Pedido> Pedido = PedidoService.getAllPedidos();
		return ResponseEntity.ok(Pedido);
	}

	@PostMapping("/")
	public ResponseEntity<Pedido> savePedidoControl(@RequestBody @Valid Pedido Pedido) {
		Pedido savePedido = PedidoService.savePedido(Pedido);
		return ResponseEntity.status(HttpStatus.CREATED).body(savePedido);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Pedido> alteraPedidoControl(@PathVariable Long id, @RequestBody @Valid Pedido Pedido) {
		Pedido alteraPedido = PedidoService.changePedido(id, Pedido);

		if (alteraPedido != null) {
			return ResponseEntity.ok(Pedido);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletePedidoControl(@PathVariable Long id) {
		boolean delete = PedidoService.deletePedido(id);
		if (delete) {
			return ResponseEntity.ok().body("O Pedido foi excluido com o sucesso");
		} else {
			return ResponseEntity.notFound().build();
		}

	}

}