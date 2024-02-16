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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.asj.api.models.order.OrderDetailModel;
import com.asj.api.services.order.OrderDetailService;
import com.asj.api.utils.ValidationUtils;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/order-details")
public class OrderDetailController {

	@Autowired
	OrderDetailService orderDetailService;

	@GetMapping("/test")
	public String test() {
		return "Pass!";
	}

	@GetMapping
	public ResponseEntity<List<OrderDetailModel>> getAllOrderDetails() {
		List<OrderDetailModel> orderDetails = orderDetailService.getAllOrderDetails();
		return ResponseEntity.ok(orderDetails);
	}

	@GetMapping(params = "orderId") // [GET] /order-details?orderId={id}
	public ResponseEntity<List<OrderDetailModel>> getOrderDetailsByOrder(@RequestParam Integer orderId) {
		List<OrderDetailModel> orderDetails = orderDetailService.getOrderDetailsByOrder(orderId);
		return ResponseEntity.ok(orderDetails);
	}

	@GetMapping("/{id}")
	public ResponseEntity<OrderDetailModel> getOrderDetailById(@PathVariable Integer id) {
		OrderDetailModel orderDetail = orderDetailService.getOrderDetailById(id);
		return ResponseEntity.ok(orderDetail);
	}
	
	@GetMapping("/top-products")
	public ResponseEntity<List<Object[]>> getTopProducts() {
		List<Object[]> products = orderDetailService.getTopProducts();
		return ResponseEntity.ok(products);
	}

	@PostMapping
	public ResponseEntity<OrderDetailModel> createOrderDetail(@Valid @RequestBody OrderDetailModel orderDetail,
			BindingResult bindingResult) {
		ValidationUtils.handleErrors(bindingResult);
		OrderDetailModel createdOrderDetail = orderDetailService.createOrderDetail(orderDetail);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdOrderDetail);
	}

	@PostMapping("/bulk-create")
	public ResponseEntity<List<OrderDetailModel>> createOrderDetails(
			@Valid @RequestBody List<OrderDetailModel> orderDetails, BindingResult bindingResult) {
		ValidationUtils.handleErrors(bindingResult);
		List<OrderDetailModel> createdOrderDetails = orderDetailService.createOrderDetails(orderDetails);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdOrderDetails);
	}

	@PutMapping("/{id}")
	public ResponseEntity<OrderDetailModel> updateOrderDetail(@PathVariable Integer id,
			@Valid @RequestBody OrderDetailModel orderDetail, BindingResult bindingResult) {
		ValidationUtils.handleErrors(bindingResult);
		OrderDetailModel updatedOrderDetail = orderDetailService.updateOrderDetail(id, orderDetail);
		return ResponseEntity.ok(updatedOrderDetail);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<OrderDetailModel> patchOrderDetail(@PathVariable Integer id,
			@Valid @RequestBody OrderDetailModel orderDetail, BindingResult bindingResult) {
		ValidationUtils.handlePartialErrors(bindingResult, orderDetail);
		OrderDetailModel patchedOrderDetail = orderDetailService.patchOrderDetail(id, orderDetail);
		return ResponseEntity.ok(patchedOrderDetail);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<OrderDetailModel> deleteOrderDetail(@PathVariable Integer id) {
		OrderDetailModel deletedOrderDetail = orderDetailService.deleteOrderDetail(id);
		return ResponseEntity.ok(deletedOrderDetail);
	}
}