package it.unisa.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {

	private ArrayList<ProductBean> products;
	
	public Cart() {
		products = new ArrayList<ProductBean>();
	}
	
	public void addProduct(ProductBean product) {
			boolean trovo=false;
			int quantitativo=1;
			for(ProductBean prod : products) {
			if(prod.getCode() == product.getCode()) {
				quantitativo=prod.getQuantità();
				quantitativo=quantitativo+1;
				//System.out.println(quantitativo);
				prod.setQuantità(quantitativo);
				trovo=true;
				break;
			}
		}
		if (trovo==false) {
			products.add(product);
		}	
	
	}
	
	public void deleteProduct(ProductBean product) {
		for(ProductBean prod : products) {
			if(prod.getCode() == product.getCode()) {
				products.remove(prod);
				break;
			}
		}
 	}
	
	public ArrayList<ProductBean> getProducts() {
		return  products;
	}
	
	public Cart clone() {
		Cart newcart= new Cart();
		ArrayList<ProductBean> array= getProducts();
		for (int i=0; i<array.size(); i++) {
			ProductBean x = array.get(i);
			newcart.addProduct(x);
		}
		
		return newcart;
	}
	
	public void deleteAll() {
		for(int i=0; i< products.size(); i++) {
			deleteProduct(products.get(i));
		}
	}
}
