package com.reinhart.onlinefoodorderingcrud.DTO;

import com.reinhart.onlinefoodorderingcrud.Entity.Order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class OrderRequest {

	private Order order;
}
