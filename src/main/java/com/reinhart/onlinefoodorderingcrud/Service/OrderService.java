package com.reinhart.onlinefoodorderingcrud.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reinhart.onlinefoodorderingcrud.Entity.Order;
import com.reinhart.onlinefoodorderingcrud.Repository.OrderRepository;
import com.reinhart.onlinefoodorderingcrud.Repository.OrdersRepository;

@Service
public class OrderService {
	@Autowired
	private OrderRepository repo;
	@Autowired
	private OrdersRepository repo2;

	public Order saveOrder(Order order) {
		return repo.save(order);
	}

	public List<Order> findOrders() {
		return repo.findAll();
	}

	public Optional<Order> findOrderByID(int ID) {
		return repo.findById(ID);
	}

	public void deleteOrder(int id) {
		repo.deleteById(id);
	}

	public Order updateOrder(Order order) {
		return repo.save(order);
	}

	public void deleteAllData() {
		repo.deleteAll();
		repo2.deleteAll();
	}

}
