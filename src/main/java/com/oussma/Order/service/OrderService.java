package com.oussma.order.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import org.springframework.web.client.RestTemplate;

import com.oussma.order.dto.OrderDTO;
import com.oussma.order.dto.OrderDTOFromFE;
import com.oussma.order.dto.UserDTO;
import com.oussma.order.entity.Order;
import com.oussma.order.mapper.OrderMapper;
import com.oussma.order.repository.OrderRepository;

@Service
@RequiredArgsConstructor
public class OrderService {


    private final OrderRepository orderRepo;
    private final SequenceGenerator sequenceGenerator;
    private final RestTemplate restTemplate;
    private final OrderMapper orderMapper;


    public OrderDTO saveOrderInDb(OrderDTOFromFE orderDetails) {
        Integer newOrderID = sequenceGenerator.generateNextOrderId();
        UserDTO userDTO = fetchUserDetailsFromUserId(orderDetails.getUserId());
        Order orderToBeSaved = new Order(newOrderID, orderDetails.getFoodItemsList(), orderDetails.getRestaurant(), userDTO );
        orderRepo.save(orderToBeSaved);

        return orderMapper.mapOrderToOrderDTO(orderToBeSaved);
    }

    private UserDTO fetchUserDetailsFromUserId(Integer userId) {
       return restTemplate.getForObject("http://USER-SERVICE/user/fetchUserById/" + userId, UserDTO.class);
    }
}
