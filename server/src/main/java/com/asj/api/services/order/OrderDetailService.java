package com.asj.api.services.order;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asj.api.exceptions.EntityNotFoundException;
import com.asj.api.exceptions.InvalidIdentifierException;
import com.asj.api.models.order.OrderDetailModel;
import com.asj.api.repositories.order.OrderDetailRepository;
import com.asj.api.services.product.ProductService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class OrderDetailService {

	@Autowired
	private OrderDetailRepository orderDetailRepository;

	@Autowired
	private OrderService orderService;

	@Autowired
	private ProductService productService;

	@PersistenceContext
	private EntityManager entityManager;

	public List<OrderDetailModel> getAllOrderDetails() {
		return orderDetailRepository.findAll();
	}

	public List<OrderDetailModel> getOrderDetailsByOrder(Integer orderId) {
		return orderDetailRepository.findByOrder_Id(orderId);
	}

	public OrderDetailModel getOrderDetailById(Integer id) {
		return orderDetailRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Order detail not found"));
	}
	
	public List<Object[]> getTopProducts() {
		return orderDetailRepository.findTopProducts();
	}

	@Transactional
	public OrderDetailModel createOrderDetail(OrderDetailModel orderDetail) {
		validateOrder(orderDetail.getOrder().getId());
		validateProduct(orderDetail.getProduct().getId());

		orderDetail.setCreatedAt(LocalDateTime.now());
		orderDetail.setUpdatedAt(LocalDateTime.now());
		orderDetail.setIsDeleted(false);
		
		OrderDetailModel createdOrderDetail = orderDetailRepository.save(orderDetail);
		entityManager.refresh(createdOrderDetail);

		return createdOrderDetail;
	}

	@Transactional
	public List<OrderDetailModel> createOrderDetails(List<OrderDetailModel> orderDetails) {
		List<OrderDetailModel> createdOrderDetails = new ArrayList<>();

		for (OrderDetailModel orderDetail : orderDetails) {
			OrderDetailModel createdOrderDetail = createOrderDetail(orderDetail);
			createdOrderDetails.add(createdOrderDetail);
		}

		return createdOrderDetails;
	}

	@Transactional
	public OrderDetailModel updateOrderDetail(Integer id, OrderDetailModel orderDetail) {
		if (!orderDetailRepository.existsById(id)) {
			throw new EntityNotFoundException("Order detail not found");
		}

		validateOrder(orderDetail.getOrder().getId());
		validateProduct(orderDetail.getProduct().getId());

		orderDetail.setUpdatedAt(LocalDateTime.now());

		OrderDetailModel updatedOrderDetail = orderDetailRepository.save(orderDetail);
		entityManager.flush();
		entityManager.refresh(updatedOrderDetail);

		return updatedOrderDetail;
	}

	@Transactional
	public OrderDetailModel patchOrderDetail(Integer id, OrderDetailModel orderDetail) {
		OrderDetailModel existingOrderDetail = getOrderDetailById(id);

		if (orderDetail.getOrder() != null) {
			validateOrder(orderDetail.getOrder().getId());
			existingOrderDetail.setOrder(orderDetail.getOrder());
		}

		if (orderDetail.getProduct() != null) {
			validateProduct(orderDetail.getProduct().getId());
			existingOrderDetail.setProduct(orderDetail.getProduct());
		}

		if (orderDetail.getQuantity() != null) {
			existingOrderDetail.setQuantity(orderDetail.getQuantity());
		}

		if (orderDetail.getPrice() != null) {
			existingOrderDetail.setPrice(orderDetail.getPrice());
		}

		if (orderDetail.getIsDeleted() != null) {
			existingOrderDetail.setIsDeleted(orderDetail.getIsDeleted());
		}

		existingOrderDetail.setUpdatedAt(LocalDateTime.now());

		orderDetailRepository.save(existingOrderDetail);
		entityManager.flush();
		entityManager.refresh(existingOrderDetail);

		return existingOrderDetail;
	}

	@Transactional
	public OrderDetailModel deleteOrderDetail(Integer id) {
		OrderDetailModel deletedOrderDetail = getOrderDetailById(id);
		orderDetailRepository.deleteById(id);

		return deletedOrderDetail;
	}

	private void validateOrder(Integer id) {
		if (!orderService.isIdValid(id)) {
			throw new InvalidIdentifierException("Order ID is not valid");
		}
	}

	private void validateProduct(Integer id) {
		if (!productService.isIdValid(id)) {
			throw new InvalidIdentifierException("Product ID is not valid");
		}
	}

}
