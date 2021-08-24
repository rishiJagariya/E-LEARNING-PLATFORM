package com.elp.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cart {
    @Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int cartId;

	//@OneToOne(mappedBy = "Student")
	//@JoinColumn(name = "userId")
	private int userId;

	//@OneToMany(mappedBy = "Course")
	//@JoinColumn(name = "courseId")
   @ElementCollection(targetClass=Integer.class)
	private List<Integer> items;

	@Column
	private int totalAmount;
	
	private int discount;

	public Cart() {

	}

	public Cart(int cartId, int studentId, List<Integer> items, int totalAmount, int discount) {
		super();
		this.cartId = cartId;
		this.userId = studentId;
		this.items = null;
		this.totalAmount = totalAmount;
		this.discount = discount;
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int studentId) {
		this.userId = studentId;
	}

	public List<Integer> getItems() {
		return items;
	}

	public void setItems(List<Integer> items) {
		this.items = items;
	}

	public int getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int totalAmount) {
		this.discount = (10 * totalAmount) / 100;
	}

	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", userId=" + userId + ", items=" + items + ", totalAmount=" + totalAmount
				+ ", discount=" + discount + "]";
	}

}