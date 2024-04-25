package com.example.orderservice.dto;

import lombok.*;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestOrder {
    private List<OrderLineItemsDto> orderLineItemsDtoList;
}
