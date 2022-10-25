package com.reinhart.onlinefoodorderingcrud.Entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ORDER_TBL")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private int OrderID;

	@Column
	private String CustName;

	@OneToMany(targetEntity = Orders.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "order_fk", referencedColumnName = "OrderID")
	private List<Orders> orders;
}
