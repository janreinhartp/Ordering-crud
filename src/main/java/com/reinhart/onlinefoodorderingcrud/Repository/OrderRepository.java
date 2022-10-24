package com.reinhart.onlinefoodorderingcrud.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reinhart.onlinefoodorderingcrud.Entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

}
