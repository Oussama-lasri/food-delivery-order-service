package com.oussma.Order.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import com.oussma.Order.dto.FoodItemDTO;
import com.oussma.Order.dto.Restaurant;
import com.oussma.Order.dto.UserDTO;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("order")
public class Order {
    private Integer orderId;
    private List<FoodItemDTO> foodItemsList;
    private Restaurant restaurant;
    private UserDTO userDTO;

}
