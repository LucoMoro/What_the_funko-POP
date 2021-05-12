package it.unisa.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {

	private ArrayList<CartItem> products;

	public Cart() {
		products = new ArrayList<CartItem>();
	}

	public void addProduct(ProductBean product) {
		for (int i=0; i<products.size(); i++) {

			if(products.get(i).getProduct().equals(product)) {
				products.get(i).incrementQuantity();
				return;
			}

		}

		CartItem cartItem = new CartItem(product);
		products.add(cartItem);
	}



	public void deleteProduct(ProductBean product) {
		for (int i=0; i<products.size(); i++) {

			if(products.get(i).getProduct().equals(product)) {
				products.remove(i);
				return;
			}	
		}
	}


	public ArrayList<CartItem> getProducts() {
		return  products;
	}



	public Cart clone() {
		Cart newcart= new Cart(); //nuovo array
		ArrayList<CartItem> array= getProducts(); //ci mettiamo tutti i prodotti di CartItem nella variabile array
		for (int i=0; i<array.size(); i++) {
			ProductBean x = array.get(i).getProduct();//scorro l'array che dovrei copiarmi. Prima copio il prodotto, 
			x.setQuantity(array.get(i).getQuantity());//poi la quantità e successivamente lo aggiungo. questo per ogni iterazione
			newcart.addProduct(x);
		}

		return newcart;
	}



	public void deleteAll() {
		for(int i=0; i< products.size(); i++) {
			deleteProduct(products.get(i).getProduct()); //cancella il prodotto in cima a CartItem iterando per ogni elemento
		}
	}
}


