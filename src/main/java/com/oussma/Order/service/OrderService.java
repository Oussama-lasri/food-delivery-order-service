package com.oussma.Order.service;

import org.springframework.stereotype.Service;

import com.oussma.Order.dto.OrderDTO;
import com.oussma.Order.dto.OrderDTOFromFE;
import com.oussma.Order.dto.UserDTO;
import com.oussma.Order.entity.Order;
import com.oussma.Order.mapper.OrderMapper;
import com.oussma.Order.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class OrderService {


    private final OrderRepository orderRepo;
    private final SequenceGenerator sequenceGenerator;
    private final RestTemplate restTemplate;
    private final OrderMapper orderMapper;


    public OrderDTO saveOrderInDb(OrderDTOFromFE orderDetails) {
        Integer newOrderID = sequenceGenerator.generateNextOrderId();
        UserDTO userDTO = null; //fetchUserDetailsFromUserId(orderDetails.getUserId());
        Order orderToBeSaved = new Order(newOrderID, orderDetails.getFoodItemsList(), orderDetails.getRestaurant(), userDTO );
        orderRepo.save(orderToBeSaved);
//    	return null ;

        return orderMapper.mapOrderToOrderDTO(orderToBeSaved);
    }

    private UserDTO fetchUserDetailsFromUserId(Integer userId) {
//    	return null ;
       return restTemplate.getForObject("http://USER-SERVICE/user/fetchUserById/" + userId, UserDTO.class);
    }
}
