package com.ds.delivery.service;

import com.ds.delivery.dto.OrderDto;
import com.ds.delivery.entities.Order;
import com.ds.delivery.entities.OrderStatus;
import com.ds.delivery.repositories.OrderRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Transactional(readOnly = true)
    public List<OrderDto> findAll() {
        List<Order> orderList = orderRepository.findOrdersWithProducts();
        return modelMapper.map(orderList, new TypeToken<List<OrderDto>>() {
        }.getType());
    }

    @Transactional
    public OrderDto insert(OrderDto orderDto) {
        orderDto.setMoment(Instant.now());
        orderDto.setStatus(OrderStatus.PENDING);
        Order order1 = dtoToEntity(orderDto);
        Order order2 = orderRepository.save(order1);
        return entityToDto(order2);
    }

    @Transactional
    public OrderDto setDelivered(Long id) {
        Order order = orderRepository.getOne(id);
        order.setStatus(OrderStatus.DELIVERED);
        order = orderRepository.save(order);
        return entityToDto(order);
    }

    public OrderDto entityToDto(Order order) {
        return modelMapper.map(order, OrderDto.class);
    }

    public Order dtoToEntity(OrderDto orderDto) {
        return modelMapper.map(orderDto, Order.class);
    }

}
