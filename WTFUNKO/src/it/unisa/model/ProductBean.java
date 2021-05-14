package it.unisa.model;

import java.io.Serializable;

public class ProductBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int code;
	private String name;
	private String description;
	private int price;
	private int quantity;
	private int rarity;
	private double dimension;
	private String FranchiseName;
	private String series;
	private double  mediumScore;
	//private int quantità;

	public ProductBean() {
		code = -1;
		name = "";
		description = "";
		quantity = 0;
		rarity=0;
		dimension=9.5;
		FranchiseName= "";
		series="";
		mediumScore=0;
		//quantità=1;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public int getRarity() {
		return rarity;
	}
	
	public void setRarity(int rarity) {
		this.rarity = rarity;
	}
	
	public double getDimension () {
		return dimension;
	}
	
	public void setDimension (double dimension) {
		this.dimension= dimension;
	}

	public String getFranchiseName() {
		return FranchiseName;
	}
	
	public void setFranchiseName(String FranchiseName) {
		this.FranchiseName= FranchiseName;
	}
	
	public String getSeries() {
		return series;
	}
	
	public void setSeries (String series) {
		this.series= series;
	}
	
	public double getMediumScore() {
		return  mediumScore;
	}
	
	public void setMediumScore (double mediumScore) {
		this.mediumScore=  mediumScore;
	}
	
	/*public int getQuantità() {
		return this.quantità;
	}
	
	public void setQuantità(int quantit) {
		this.quantità=quantit;
	}*/
	
	
	
	@Override
	public String toString() {
		return name + " (" + code + "), " + price + " " + quantity + ". " + description;
	}

}
