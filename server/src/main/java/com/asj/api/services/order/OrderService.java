package com.asj.api.services.order;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asj.api.exceptions.AssociatedEntitiesException;
import com.asj.api.exceptions.EntityNotFoundException;
import com.asj.api.exceptions.InvalidIdentifierException;
import com.asj.api.exceptions.UniqueViolationException;
import com.asj.api.models.order.OrderModel;
import com.asj.api.repositories.order.OrderDetailRepository;
import com.asj.api.repositories.order.OrderRepository;
import com.asj.api.services.supplier.SupplierService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private StatusService statusService;

	@Autowired
	private SupplierService supplierService;

	@Autowired
	private OrderDetailRepository orderDetailRepository;

	@PersistenceContext
	private EntityManager entityManager;

	public List<OrderModel> getAllOrders() {
		return orderRepository.findAll();
	}

	public OrderModel getOrderById(Integer id) {
		return orderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Order not found"));
	}
	
	public List<Object[]> getTopSuppliers() {
		return orderRepository.findTopSuppliers();
	}
	
	public List<Object[]> getMonthsTotal() {
		return orderRepository.findMonthsTotal();
	}

	@Transactional
	public OrderModel createOrder(OrderModel order) {
		if (!isNumberUnique(order.getNumber())) {
			throw new UniqueViolationException("Order number must be unique");
		}

		validateStatus(order.getStatus().getId());
		validateSupplier(order.getSupplier().getId());

		order.setCreatedAt(LocalDateTime.now());
		order.setUpdatedAt(LocalDateTime.now());
		order.setIsDeleted(false);

		OrderModel createdOrder = orderRepository.save(order);
		entityManager.refresh(createdOrder);

		return createdOrder;
	}

	@Transactional
	public OrderModel updateOrder(Integer id, OrderModel order) {
		if (!orderRepository.existsById(id)) {
			throw new EntityNotFoundException("Order not found");
		}

		if (!isNumberUniqueAndIdNot(order.getNumber(), id)) {
			throw new UniqueViolationException("Order number must be unique");
		}

		validateStatus(order.getStatus().getId());
		validateSupplier(order.getSupplier().getId());

		order.setUpdatedAt(LocalDateTime.now());

		OrderModel updatedOrder = orderRepository.save(order);
		entityManager.flush();
		entityManager.refresh(updatedOrder);

		return updatedOrder;
	}

	@Transactional
	public OrderModel patchOrder(Integer id, OrderModel order) {
		OrderModel existingOrder = getOrderById(id);

		if (order.getNumber() != null) {
			if (!isNumberUniqueAndIdNot(order.getNumber(), id)) {
				throw new UniqueViolationException("Order number must be unique");
			}
			existingOrder.setNumber(order.getNumber());
		}

		if (order.getIssueDate() != null) {
			existingOrder.setIssueDate(order.getIssueDate());
		}

		if (order.getDeliveryDate() != null) {
			existingOrder.setDeliveryDate(order.getDeliveryDate());
		}

		if (order.getComments() != null) {
			existingOrder.setComments(order.getComments());
		}

		if (order.getTotal() != null) {
			existingOrder.setTotal(order.getTotal());
		}

		if (order.getStatus() != null) {
			validateStatus(order.getStatus().getId());
			existingOrder.setStatus(order.getStatus());
		}

		if (order.getSupplier() != null) {
			validateSupplier(order.getSupplier().getId());
			existingOrder.setSupplier(order.getSupplier());
		}

		if (order.getUser() != null) {
			existingOrder.setUser(order.getUser());
		}

		if (order.getIsDeleted() != null) {
			existingOrder.setIsDeleted(order.getIsDeleted());
		}

		existingOrder.setUpdatedAt(LocalDateTime.now());

		orderRepository.save(existingOrder);
		entityManager.flush();
		entityManager.refresh(existingOrder);

		return existingOrder;
	}

	@Transactional
	public OrderModel deleteOrder(Integer id) {
		OrderModel deletedOrder = getOrderById(id);

		if (orderDetailRepository.countByOrder(id) > 0) {
			throw new AssociatedEntitiesException();
		}

		orderRepository.deleteById(id);

		return deletedOrder;
	}

	public String generateOrderNumber() {
		LocalDateTime todayDate = LocalDateTime.now();
		String formattedDate = todayDate.format(DateTimeFormatter.BASIC_ISO_DATE); // yyyy-MM-dd
		int orderCount = orderRepository.countByIssueDate(todayDate);
		String orderNumber = "OC-" + formattedDate + "-" + (orderCount + 1);

		return orderNumber;
	}

	private boolean isNumberUnique(String number) {
		return !orderRepository.existsByNumber(number);
	}

	private boolean isNumberUniqueAndIdNot(String number, Integer id) {
		return !orderRepository.existsByNumberAndIdNot(number, id);
	}

	public boolean isIdValid(Integer id) {
		return orderRepository.existsById(id);
	}

	private void validateStatus(Integer id) {
		if (!statusService.isIdValid(id)) {
			throw new InvalidIdentifierException("Status ID is not valid");
		}
	}

	private void validateSupplier(Integer id) {
		if (!supplierService.isIdValid(id)) {
			throw new InvalidIdentifierException("Supplier ID is not valid");
		}
	}

}
