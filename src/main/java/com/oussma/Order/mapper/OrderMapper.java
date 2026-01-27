package com.oussma.Order.mapper;

import com.oussma.Order.dto.OrderDTO;
import com.oussma.Order.entity.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {

		Order mapOrderDTOToOrder(OrderDTO orderDTO);
	    OrderDTO mapOrderToOrderDTO(Order order);
}
