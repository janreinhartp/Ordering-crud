package com.reinhart.onlinefoodorderingcrud;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyList;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ResourceUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.reinhart.onlinefoodorderingcrud.DTO.OrderRequest;
import com.reinhart.onlinefoodorderingcrud.Entity.Orders;
import com.reinhart.onlinefoodorderingcrud.Service.OrderService;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CrudJUnitTest {
	private static final Logger log = LoggerFactory.getLogger(CrudJUnitTest.class);

	@Autowired
	private OrderService service;

	static int orderIDfromTest;

	static com.reinhart.onlinefoodorderingcrud.Entity.Order orderFromSave;
	static List<Orders> ordersFromSave;

	@Test
	@Order(1)
	public final void testAddOrder() throws JsonMappingException, JsonProcessingException {
		service.deleteAllData();
		log.info("--- Start Test AddOrder ---");
		log.info("--- Expected Results :  ---");
		OrderRequest ordertoadd = new ObjectMapper().readValue(readJsonFile("toadd"), OrderRequest.class);
		log.info(ordertoadd.toString());
		orderFromSave = service.saveOrder(ordertoadd.getOrder());

		// Setting Order from Add Function

		log.info("--- Data Added to Database :  ---");
		log.info(orderFromSave.toString());
		orderIDfromTest = orderFromSave.getOrderID();
		ordersFromSave = orderFromSave.getOrders();
		assertThat(orderFromSave).usingRecursiveComparison().ignoringFields("OrderID").isEqualTo(ordertoadd.getOrder());
	}

	@Test
	@Order(2)
	public final void testGetAllOrders() throws JsonMappingException, JsonProcessingException {

		log.info("--- Start Test testGetOrder ---");
		log.info("--- Expected Results :  ---");

		OrderRequest ordertoadd = new ObjectMapper().readValue(readJsonFile("tocheck"), OrderRequest.class);

		List<com.reinhart.onlinefoodorderingcrud.Entity.Order> datatocheck = anyList();
		datatocheck.add(ordertoadd.getOrder());

		log.info(ordertoadd.getOrder().toString());

		List<com.reinhart.onlinefoodorderingcrud.Entity.Order> datafromDB = service.findOrders();

		log.info("--- Data from Database :  with Order ID---");
		datafromDB.forEach(data -> {
			log.info(data.toString());
		});

		assertThat(datafromDB).usingRecursiveComparison().ignoringFields("OrderID", "orders.OrdersID")
				.isEqualTo(datatocheck);
	}

	@Test
	@Order(3)
	public final void testUpdateOrder() throws JsonMappingException, JsonProcessingException {

		log.info("--- Start Test testUpdateOrder ---");
		log.info("--- Expected Results :  ---");
		OrderRequest orderFromFile = new ObjectMapper().readValue(readJsonFile("toupdate"), OrderRequest.class);

		com.reinhart.onlinefoodorderingcrud.Entity.Order orderFromFileWithID = orderFromFile.getOrder();
		for (Orders orders : ordersFromSave) {
			orders.setItem("Updated Item");
			orders.setPrice(1);
			orders.setQty(1);
		}

		orderFromFileWithID.setOrders(ordersFromSave);
		orderFromFileWithID.setOrderID(orderIDfromTest);
		log.info(orderFromFileWithID.toString());

		com.reinhart.onlinefoodorderingcrud.Entity.Order datafromDB = service.updateOrder(orderFromFileWithID);
		log.info("--- Data from Database :  with Order ID---");
		log.info(datafromDB.toString());
		assertThat(datafromDB).usingRecursiveComparison().ignoringFields("orders.OrdersID")
				.isEqualTo(orderFromFile.getOrder());
	}

	public String readJsonFile(String filename) {
		try {
			String filepath = "classpath:json/" + filename + ".json";
			File file = ResourceUtils.getFile(filepath);
			@SuppressWarnings("resource")
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = null;
			StringBuilder builder = new StringBuilder();

			while ((line = reader.readLine()) != null) {
				builder.append(line);
			}

			String jsonContent = builder.toString();

			return jsonContent;

		} catch (FileNotFoundException e) {
			log.error(e.toString());
		} catch (IOException e) {
			log.error(e.toString());
		}
		return null;
	}

}
