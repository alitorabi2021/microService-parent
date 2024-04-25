package com.example.orderservice.model;


import javax.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "order_tbl")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String orderNumber;
    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderLineItem> orderLineItems;
}
