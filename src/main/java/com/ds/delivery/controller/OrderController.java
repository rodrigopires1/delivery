package com.ds.delivery.controller;

import com.ds.delivery.dto.OrderDto;
import com.ds.delivery.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    @Autowired
    private OrderService service;

    @GetMapping
    public ResponseEntity<List<OrderDto>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<OrderDto> insert(@RequestBody OrderDto dto) {
        return ResponseEntity.ok().body(service.insert(dto));
    }

    @PutMapping(value = "/{id}/delivered")
    public ResponseEntity<OrderDto> setDelivered (@PathVariable Long id) {
        return ResponseEntity.ok().body(service.setDelivered(id));
    }

}
