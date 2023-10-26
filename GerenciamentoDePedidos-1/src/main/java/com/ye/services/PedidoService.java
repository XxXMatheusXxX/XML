package com.ye.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ye.entities.Pedido;
import com.ye.repository.PedidoRepository;

@Service
public class PedidoService {
	private final PedidoRepository PedidoRepository;
	

	@Autowired
	public PedidoService(PedidoRepository PedidoRepository) {
		this.PedidoRepository = PedidoRepository;
	}

	public List<Pedido> getAllPedidos() {
		return PedidoRepository.findAll();
	}

	public Pedido getPedidoById(Long id) {
		Optional<Pedido> Pedido = PedidoRepository.findById(id);
		return Pedido.orElse(null);
	}

	public Pedido savePedido(Pedido Pedido) {
		return PedidoRepository.save(Pedido);
	}

	public Pedido changePedido(Long id, Pedido changeU) {
		Optional<Pedido> existePedido = PedidoRepository.findById(id);
		if (existePedido.isPresent()) {
			changeU.setId(id);
			return PedidoRepository.save(changeU);
		}
		return null;
	}

	public boolean deletePedido(Long id) {
		Optional<Pedido> existePedido= PedidoRepository.findById(id);
		if (existePedido.isPresent()) {
			PedidoRepository.deleteById(id);
			return true;
		}
		return false;
	}

}