package it.unisa.model;

public class OrderItem {
	private int productCode;
	private int orderQuantity;
	private int orderCode;
	
	
	public OrderItem(CartItem cartItem) { //in cartItem ci sono tutti i prodotti e la quantità
		productCode= cartItem.getProduct().getCode(); //così noi prendiamo solo il codice del prodotto e non tutti i valori
		orderQuantity= cartItem.getQuantity();
		orderCode=0;
	}
	
	public OrderItem() {
		
	}
	
	public int getOrderQuantity() {
		return orderQuantity;
	}
	
	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity= orderQuantity;
	}
	
	public int getOrderCode() {
		return orderCode;
	}
	
	public void setOrderCode(int x) {
		this.orderCode= x;
	}
	
	public int getProductCode() {
		return productCode;
	}
	
	public void setProductCode(int z) {
		this.productCode= z;
	}
	
}
