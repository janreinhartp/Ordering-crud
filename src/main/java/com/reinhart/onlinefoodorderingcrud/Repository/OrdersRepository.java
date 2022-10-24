package com.reinhart.onlinefoodorderingcrud.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reinhart.onlinefoodorderingcrud.Entity.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Integer> {

}
