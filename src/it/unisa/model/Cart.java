package it.unisa.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {

	private List<ProductBean> products;
	
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
				System.out.println(quantitativo);
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
	
	public List<ProductBean> getProducts() {
		return  products;
	}
}
