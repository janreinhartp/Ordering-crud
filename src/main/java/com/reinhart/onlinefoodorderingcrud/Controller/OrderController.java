package com.reinhart.onlinefoodorderingcrud.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.reinhart.onlinefoodorderingcrud.DTO.OrderRequest;
import com.reinhart.onlinefoodorderingcrud.Entity.Order;
import com.reinhart.onlinefoodorderingcrud.Service.OrderService;

@RestController
public class OrderController {

	@Autowired
	private OrderService service;

	@PostMapping("/addOrder")
	public Order addOrder(@RequestBody OrderRequest order) {
		return service.saveOrder(order.getOrder());
	}

	@GetMapping("/getOrders")
	public List<Order> getAllOrders() {
		return service.findOrders();
	}

	@PutMapping("/updateOrder")
	public Order updateOrder(@RequestBody OrderRequest order) {
		return service.updateOrder(order.getOrder());
	}

	@DeleteMapping("/delete/{id}")
	public String deleteOrder(@PathVariable int id) {
		try {
			service.deleteOrder(id);
			return "Sucessfully Deleted!";
		} catch (Exception e) {
			return e.toString();
		}
	}

}
