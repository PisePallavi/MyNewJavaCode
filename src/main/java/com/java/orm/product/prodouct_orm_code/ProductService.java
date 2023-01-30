package com.java.orm.product.prodouct_orm_code;

import java.util.List;

import com.java.user.exception.DuplicateProductException;
import com.java.user.exception.InvalidPriceException;
import com.java.user.exception.InvalidProductIdException;
import com.java.user.exception.NegativePriceException;

public interface ProductService {
	public String addProduct(ProductInfo prod)throws DuplicateProductException,InvalidProductIdException,NegativePriceException;//
	public List<ProductInfo> ListProduct();//
	public ProductInfo searchProduct(int prodId)throws InvalidProductIdException;//
	public boolean deleteProduct(int prodId)throws InvalidProductIdException;//
	public ProductInfo maxPriceProduct();//
	public ProductInfo minPriceProduct();//
	public List<ProductInfo> salaryRange(int price1, double price2)throws InvalidPriceException,NegativePriceException;
	public ProductInfo updateProduct(int pid,ProductInfo product)throws NegativePriceException;//
	public double avgPriceProduct(); 
}
