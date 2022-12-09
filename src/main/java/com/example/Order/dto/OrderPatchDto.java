package com.example.Order.dto;

import com.example.Order.entity.Order;
import lombok.Getter;
import org.mapstruct.Mapper;

@Getter
public class OrderPatchDto {
    private long orderId;
    private Order.OrderStatus orderStatus;

    public void setOrderId(long orderId){
        this.orderId = orderId;
    }
}
