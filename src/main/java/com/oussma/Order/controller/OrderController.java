package com.oussma.order.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oussma.order.dto.OrderDTO;
import com.oussma.order.dto.OrderDTOFromFE;
import com.oussma.order.service.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/order")
@RequiredArgsConstructor
public class OrderController {
	
	
	 
	    OrderService orderService;

	    @PostMapping("/saveOrder")
	    public ResponseEntity<OrderDTO> saveOrder(@RequestBody OrderDTOFromFE orderDetails){
	        OrderDTO orderSavedInDB = orderService.saveOrderInDb(orderDetails);
	        return new ResponseEntity<>(orderSavedInDB, HttpStatus .CREATED);
	    }

}
