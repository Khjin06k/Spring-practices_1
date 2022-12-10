package com.example.Order.mapper;

import com.example.Order.dto.OrderPatchDto;
import com.example.Order.dto.OrderPostDto;
import com.example.Order.dto.OrderResponseDto;
import com.example.Order.entity.Order;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    Order orderPostDtoToOrder(OrderPostDto orderPostDto);
    Order orderPatchDtoToOrder(OrderPatchDto orderPatchDto);

    Order orderToOrderResponseDto(Order order);
    List<OrderResponseDto> orderToOrderResponseDtos(List<Order> orders);
}
