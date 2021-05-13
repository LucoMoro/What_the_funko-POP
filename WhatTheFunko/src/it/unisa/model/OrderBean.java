package it.unisa.model;

import java.io.Serializable;
import java.util.ArrayList;

public class OrderBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String ID;
	
	private String dataConsegna;//
	private double iva;
	private String dataOrdine;//
	private int  orderCode;
	private ArrayList<OrderItem> orderItem;
	

	public OrderBean() {
		ID="";
		dataConsegna="";
		iva=22;
		dataOrdine="";
		orderCode=0;
		orderItem= new ArrayList<OrderItem>();
	}


	public String getID() {
		return ID;
	}

	public void setID(String ID) {
		this.ID = ID;
	}

	
	public ArrayList<OrderItem> getOrderItems(){
		return orderItem;
	}
	
	public void setList(ArrayList<OrderItem> orderItem) {
		this.orderItem =orderItem;
	}
	

	public String getDataConsegna() {
		return dataConsegna;
	}
	
	public void setDataConsegna(String dataConsegna) {
		this.dataConsegna= dataConsegna;
	}
	
	
	public int getOrderCode() {
		return orderCode;
	}
	
	public void setOrderCode(int x) {
		this.orderCode= x;
	}
	
	public double getIva() {
		return iva;
	}
	
	public void setIva(double iva) {
		this.iva=iva;
	}

	public String getDataOrdine() {
		return dataOrdine;
	}
	
	public void setDataOrdine(String dataOrdine) {
		this.dataOrdine= dataOrdine;
	}
	
}
