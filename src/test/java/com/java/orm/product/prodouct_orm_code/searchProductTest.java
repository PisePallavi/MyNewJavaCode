package com.java.orm.product.prodouct_orm_code;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.java.user.exception.InvalidPriceException;
import com.java.user.exception.InvalidProductIdException;



public class searchProductTest {

	@Test
	public void successfullSearch() throws InvalidProductIdException
	{
		//ProductInfo product = new ProductInfo();
		ProdServiceImpl serv = new ProdServiceImpl();
		ProductInfo prod = serv.searchProduct(1);
		Assert.assertEquals(prod, "Product Find");
		//Assert.assertEquals(prod, "Product Find");
	}
	
	@Test
	public void invalidIdSearch() throws InvalidProductIdException
	{
		ProdServiceImpl serv = new ProdServiceImpl();
		ProductInfo prod = serv.searchProduct(2);
		Assert.assertEquals(prod, "Product Find");
	}
	
	@Test
	public void produtFindBetSalary() throws InvalidPriceException {
		ProdServiceImpl serv = new ProdServiceImpl();
		List<ProductInfo> prod = serv.salaryRange(100, 300);
		Assert.assertEquals(prod, "Products Find");
	}
	
}
