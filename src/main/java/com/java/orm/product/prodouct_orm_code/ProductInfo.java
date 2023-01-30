package com.java.orm.product.prodouct_orm_code;

import javax.persistence.Entity;
import javax.persistence.Table;

//@Entity
//@Table(name = "product_table")

public class ProductInfo {
	
	private int prod_Id;
	private String prod_Name;
	private int prod_price;
	private String prod_vendor;
	private int prod_qty;

	public ProductInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProductInfo(int prod_Id, String prod_Name, int prod_price, String prod_vendor, int prod_qty) {
		super();
		this.prod_Id = prod_Id;
		this.prod_Name = prod_Name;
		this.prod_price = prod_price;
		this.prod_vendor = prod_vendor;
		this.prod_qty = prod_qty;
	}
	public int getProd_Id() {
		return prod_Id;
	}
	public void setProd_Id(int prod_Id) {
		this.prod_Id = prod_Id;
	}
	public String getProd_Name() {
		return prod_Name;
	}
	public void setProd_Name(String prod_Name) {
		this.prod_Name = prod_Name;
	}
	public int getProd_price() {
		return prod_price;
	}
	public void setProd_price(int prod_price) {
		this.prod_price = prod_price;
	}
	public String getProd_vendor() {
		return prod_vendor;
	}
	public void setProd_vendor(String prod_vendor) {
		this.prod_vendor = prod_vendor;
	}
	public int getProd_qty() {
		return prod_qty;
	}
	public void setProd_qty(int prod_qty) {
		this.prod_qty = prod_qty;
	}
	@Override
	public String toString() {
		return "\n ProductInfo [prod_Id=" + prod_Id + ", prod_Name=" + prod_Name + ", prod_price=" + prod_price
				+ ", prod_vendor=" + prod_vendor + ", prod_qty=" + prod_qty + "]";
	}	
}
