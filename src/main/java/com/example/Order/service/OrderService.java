package com.example.Order.service;

import com.example.Member.service.MemberService;
import com.example.Order.entity.Order;
import com.example.Order.repository.OrderRepository;
import com.example.exception.BusinessLogicException;
import com.example.exception.ExceptionCode;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class OrderService {
    private final MemberService memberService;
    private final OrderRepository orderRepository;

    public OrderService(MemberService memberService, OrderRepository orderRepository) {
        this.memberService = memberService;
        this.orderRepository = orderRepository;
    }

    public Order createOrder(Order order){
        memberService.findVerifiedMember(order.getMember().getMemberId());
        return orderRepository.save(order);
    }

    public Order updateOrder(Order order){
        Order findOrder = findVerifiedOrder(order.getOrderId());

        Optional.ofNullable(order.getOrderStatus())
                .ifPresent(orderStatus -> findOrder.setOrderStatus());

        findOrder.setModifiedAt(LocalDateTime.now());
        return orderRepository.save(findOrder);
    }

    public Order findOrder(long orderId){
        return findVerifiedOrder(orderId);
    }

    public Order findOrders(int page, int size){
        return orderRepository.findAll(PageRequest.of(page, size,
                Sort.by("orderId").descending()));
    }

    public void cancleOrder(long orderId){
        Order findOrder = findVerifiedOrder(orderId);
        int step = findOrder.getOrderStatus().getStepNumber();

        if (step >= 2) {
            throw new BusinessLogicException(ExceptionCode.CANNOT_CHANGE_ORDER);
        }
        findOrder.setOrderStatus(Order.OrderStatus.ORDER_CANCEL);
        findOrder.setModifiedAt(LocalDateTime.now());
        orderRepository.save(findOrder);
    }

    private Order findVerifiedOrder(long orderId) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        Order findOrder =
                optionalOrder.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.ORDER_NOT_FOUND));
        return findOrder;
    }
}
