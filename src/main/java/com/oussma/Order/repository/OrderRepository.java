package com.oussma.order.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.oussma.order.entity.Order;

public interface OrderRepository extends MongoRepository<Order, Integer> {

}
