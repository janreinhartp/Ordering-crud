package com.reinhart.onlinefoodorderingcrud.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ORDERS_TBL")
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private int OrdersID;

	@Column
	private String Item;
	@Column
	private int Qty;
	@Column
	private int Price;

}
