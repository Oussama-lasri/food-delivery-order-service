package com.oussma.order.service;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import com.oussma.order.dto.OrderDTO;
import com.oussma.order.dto.OrderDTOFromFE;
import com.oussma.order.dto.Restaurant;
import com.oussma.order.entity.Order;
import com.oussma.order.mapper.OrderMapper;
import com.oussma.order.repository.OrderRepository;


class OrderServiceTest {
	 @Mock
	    private OrderRepository orderRepo;

	    @Mock
	    private SequenceGenerator sequenceGenerator;

	    @Mock
	    private RestTemplate restTemplate;
	    
	    @Mock
	    private OrderMapper orderMapper;

	    @InjectMocks
	    private OrderService orderService;

	    @BeforeEach
	    void setUp() {
	        MockitoAnnotations.openMocks(this);
	    }

	    @Test
	    void saveOrderInDb_shouldSaveOrderAndReturnOrderDTO() {
	    	// Arrange
	        Integer newOrderId = 1;

	        OrderDTOFromFE orderDetails = new OrderDTOFromFE();
	        Restaurant restaurant = new Restaurant();
	        orderDetails.setUserId(10);
	        orderDetails.setRestaurant(restaurant);
	        orderDetails.setFoodItemsList(null);


	        Order order = new Order(
	                newOrderId,
	                orderDetails.getFoodItemsList(),
	                orderDetails.getRestaurant(),
	                null
	        );

	        OrderDTO orderDTO = new OrderDTO();

	        when(sequenceGenerator.generateNextOrderId()).thenReturn(newOrderId);
	        when(orderRepo.save(any(Order.class))).thenReturn(order);
	        when(orderMapper.mapOrderToOrderDTO(any(Order.class))).thenReturn(orderDTO);

	        // Act
	        OrderDTO result = orderService.saveOrderInDb(orderDetails);

	        // Assert
	        assertNotNull(result);

	        verify(sequenceGenerator, times(1)).generateNextOrderId();
	        verify(orderRepo, times(1)).save(any(Order.class));
	        verify(orderMapper, times(1)).mapOrderToOrderDTO(any(Order.class));

	        verifyNoMoreInteractions(orderRepo, sequenceGenerator, orderMapper);
	    	    }

}
