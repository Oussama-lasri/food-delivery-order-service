package com.oussma.Order.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.oussma.Order.entity.Order;

public interface OrderRepository extends MongoRepository<Order, Integer> {

}
