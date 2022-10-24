package com.reinhart.onlinefoodorderingcrud.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reinhart.onlinefoodorderingcrud.Entity.Order;
import com.reinhart.onlinefoodorderingcrud.Repository.OrderRepository;

@Service
public class OrderService {
	@Autowired
	private OrderRepository repo;

	public Order saveOrder(Order order) {
		return repo.save(order);
	}

	public List<Order> findOrders() {
		return repo.findAll();
	}

	public void deleteOrder(int id) {
		repo.deleteById(id);
	}

	public Order updateOrder(Order order) {
		return repo.save(order);
	}

}
