package com.oussma.order.mapper;

import org.mapstruct.Mapper;

import com.oussma.order.dto.OrderDTO;
import com.oussma.order.entity.Order;

@Mapper(componentModel = "spring")
public interface OrderMapper {

		Order mapOrderDTOToOrder(OrderDTO orderDTO);
	    OrderDTO mapOrderToOrderDTO(Order order);
}
