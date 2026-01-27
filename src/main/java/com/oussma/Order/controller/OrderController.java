package com.oussma.Order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oussma.Order.dto.OrderDTO;
import com.oussma.Order.dto.OrderDTOFromFE;
import com.oussma.Order.service.OrderService;

@RestController
@RequestMapping(path = "/order")
public class OrderController {
	
	
	 	@Autowired
	    OrderService orderService;

	    @PostMapping("/saveOrder")
	    public ResponseEntity<OrderDTO> saveOrder(@RequestBody OrderDTOFromFE orderDetails){
	        OrderDTO orderSavedInDB = orderService.saveOrderInDb(orderDetails);
	        return new ResponseEntity<>(orderSavedInDB, HttpStatus .CREATED);
	    }

}
