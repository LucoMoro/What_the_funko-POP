package it.unisa.model;

import java.io.Serializable;
import java.util.ArrayList;

public class OrderBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String ID;
	
	private String dataConsegna;
	private double iva;
	private String dataOrdine;
	private String orderCode;
	private ArrayList<OrderItem> orderItem;
	

	public OrderBean() {
		ID="";
		dataConsegna="";
		iva=0;
		dataOrdine="";
		orderCode="";
		orderItem= new ArrayList<OrderItem>();
	}


	public String getID() {
		return ID;
	}

	public void setID(String ID) {
		this.ID = ID;
	}

	
	/*public int size() {
		return orderItem.size();
	}
	
	public OrderItem get(int i) {
		return orderItem.get(i);
	}*/
	
	public ArrayList<OrderItem> getOrderItems(){
		return orderItem;
	}
	
	public void setList(ArrayList<OrderItem> orderItem) {
		this.orderItem =orderItem;
	}
	
	/*public String getCodiceOrdine(OrderItem x) {
		return x.getOrderCode();
	}

	public void setCodiceOrdine(OrderItem x, String codiceOrdine) {
		x.setOrderCode(codiceOrdine);
	}*/


	public String getDataConsegna() {
		return dataConsegna;
	}
	
	public void setDataConsegna(String dataConsegna) {
		this.dataConsegna= dataConsegna;
	}
	
	/*public int getProductCode(OrderItem orderItem) {
		return orderItem.getProductCode();
	}

	public void setProductCode(OrderItem orderItem, int codice) {
		orderItem.setProductCode(codice);
	}
	
	public int getOrderQuantity(OrderItem orderItem) {
		return orderItem.getOrderQuantity();
	}
	
	public void setOrderQuantity(OrderItem orderItem, int orderQuantity) {
		orderItem.setOrderQuantity(orderQuantity);
	}*/
	
	public String getOrderCode() {
		return orderCode;
	}
	
	public void setOrderCode(String x) {
		this.orderCode= x;
	}
	
	public double getIva() {
		return iva;
	}

	public void setIva(double iva) {
		this.iva = iva;
	}
	
	public String getDataOrdine() {
		return dataConsegna;
	}
	
	public void setDataOrdine(String dataOrdine) {
		this.dataOrdine= dataOrdine;
	}
	
}
