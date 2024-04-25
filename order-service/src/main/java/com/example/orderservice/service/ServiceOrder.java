package com.example.orderservice.service;

import com.example.orderservice.config.WebClientConfig;
import com.example.orderservice.dto.AmountResponse;
import com.example.orderservice.dto.OrderLineItemsDto;
import com.example.orderservice.dto.RequestOrder;
import com.example.orderservice.model.Order;
import com.example.orderservice.model.OrderLineItem;
import com.example.orderservice.repository.RepositoryOrder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class  ServiceOrder {
    private final RepositoryOrder repositoryOrder;

    private final WebClient.Builder webClientBuilder;

    public String placeOrder(RequestOrder requestOrder){
        Order order=new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItem> orderLineItems=requestOrder
                .getOrderLineItemsDtoList().stream()
                .map(this::mapToDto).toList();

        order.setOrderLineItems(orderLineItems);
        List<String> skuCods= order.getOrderLineItems().stream().map(OrderLineItem::getSkuCode).toList();
        AmountResponse[] client=webClientBuilder.build().get().uri("http://amount-Service/api/amount"
                        ,uriBuilder -> uriBuilder.queryParam("skuCode",skuCods).build())
                .retrieve().bodyToMono(AmountResponse[].class).block();

                Boolean aBoolean=Arrays.stream(client)
                        .allMatch(AmountResponse::isInStock);
         if (aBoolean){
             repositoryOrder.save(order);
             return "post data is successfully";
         }else {
             throw new IllegalArgumentException("Product is not stack");
         }


    }
    private OrderLineItem mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItem orderLineItem=new OrderLineItem();
        orderLineItem.setSkuCode(orderLineItemsDto.getSkuCode());
        orderLineItem.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItem.setPrice(orderLineItemsDto.getPrice());
        return orderLineItem;
    }

}
