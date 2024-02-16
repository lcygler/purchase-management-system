package com.asj.api.controllers.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asj.api.models.order.OrderDetailModel;
import com.asj.api.models.order.OrderModel;
import com.asj.api.services.order.OrderDetailService;
import com.asj.api.services.order.OrderService;
import com.asj.api.utils.ValidationUtils;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	OrderService orderService;

	@Autowired
	OrderDetailService orderDetailService;

	@GetMapping("/test")
	public String test() {
		return "Pass!";
	}

	@GetMapping
	public ResponseEntity<List<OrderModel>> getAllOrders() {
		List<OrderModel> orders = orderService.getAllOrders();
		return ResponseEntity.ok(orders);
	}

	@GetMapping("/{id}")
	public ResponseEntity<OrderModel> getOrderById(@PathVariable Integer id) {
		OrderModel order = orderService.getOrderById(id);
		return ResponseEntity.ok(order);
	}

	@GetMapping("/{id}/details")
	public ResponseEntity<List<OrderDetailModel>> getOrderDetailsByOrder(@PathVariable Integer id) {
		List<OrderDetailModel> orderDetails = orderDetailService.getOrderDetailsByOrder(id);
		return ResponseEntity.ok(orderDetails);
	}

	@GetMapping("/number")
	public ResponseEntity<String> getOrderNumber() {
		String number = orderService.generateOrderNumber();
		return ResponseEntity.ok(number);
	}
	
	@GetMapping("/top-suppliers")
	public ResponseEntity<List<Object[]>> getTopSuppliers() {
		List<Object[]> suppliers = orderService.getTopSuppliers();
		return ResponseEntity.ok(suppliers);
	}
	
	@GetMapping("/months-total")
	public ResponseEntity<List<Object[]>> getMonthsTotal() {
		List<Object[]> total = orderService.getMonthsTotal();
		return ResponseEntity.ok(total);
	}

	@PostMapping
	public ResponseEntity<OrderModel> createOrder(@Valid @RequestBody OrderModel order, BindingResult bindingResult) {
		ValidationUtils.handleErrors(bindingResult);
		OrderModel createdOrder = orderService.createOrder(order);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
	}

	@PutMapping("/{id}")
	public ResponseEntity<OrderModel> updateOrder(@PathVariable Integer id, @Valid @RequestBody OrderModel order,
			BindingResult bindingResult) {
		ValidationUtils.handleErrors(bindingResult);
		OrderModel updatedOrder = orderService.updateOrder(id, order);
		return ResponseEntity.ok(updatedOrder);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<OrderModel> patchOrder(@PathVariable Integer id, @Valid @RequestBody OrderModel order,
			BindingResult bindingResult) {
		ValidationUtils.handlePartialErrors(bindingResult, order);
		OrderModel patchedOrder = orderService.patchOrder(id, order);
		return ResponseEntity.ok(patchedOrder);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<OrderModel> deleteOrder(@PathVariable Integer id) {
		OrderModel deletedOrder = orderService.deleteOrder(id);
		return ResponseEntity.ok(deletedOrder);
	}
}
