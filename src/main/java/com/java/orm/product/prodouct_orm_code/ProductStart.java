package com.java.orm.product.prodouct_orm_code;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.java.user.exception.DuplicateProductException;
import com.java.user.exception.InvalidPriceException;
import com.java.user.exception.InvalidProductIdException;
import com.java.user.exception.NegativePriceException;

public class ProductStart {

	public static void main(String[] args) throws NegativePriceException  {
		// TODO Auto-generated method stub
		ProdServiceImpl service = new ProdServiceImpl();
		Scanner sc = new Scanner(System.in);
		int count =0;
		while(true)
		{
		System.out.println("\n 1: Add Product " 
		+ "\n 2: List Product "
		+ "\n 3: Search Product By Id" 
		+ "\n 4: Delete Product " 
		+ "\n 5: Maxium Price Product "
		+ "\n 6: Minimun Price Product"
		+ "\n 7: Products Within Range "
		+ "\n 8. Update Products "
		+ "\n 9. Avg of Prices is "
		+ "\n 10: Exit ");
		int ch = 0;
		
		try
		{
			System.out.println("Enter Your Choice :");
			ch = Integer.parseInt(sc.next());
		}
		catch (NumberFormatException e) {
			System.out.println("Enter Valid Choice");
		}
		switch (ch) {
		case 10:
			System.out.println("Exiting Application");
			System.exit(0);
			break;
		case 1:
			try
			{
				System.out.println("Add Product : ");
				System.out.println("Enter Product ID");
				int pid = sc.nextInt();
				System.out.println("Enter Product Name");
				String pname = sc.next();
				System.out.println("Enter Product Price");
				int prodprice = sc.nextInt();
				System.out.println("Enter Product Vendor");
				String prodvendor = sc.next();
				System.out.println("Enter Product Quantity");
				int prodqty = sc.nextInt();
				ProductInfo prodobj = new ProductInfo(pid, pname, prodprice, prodvendor, prodqty);
				service.addProduct(prodobj);
			}
			catch (InvalidProductIdException e) {
				// TODO Auto-generated catch block
				System.out.println("Enter Valid Id");
			} 
			catch (DuplicateProductException e) {
				// TODO Auto-generated catch block
				System.out.println("Duplicate Product pls Add New Product");
			}
			
			catch (InputMismatchException e) {
				System.out.println("Enter Valid Product Details");
			} 
			break;
			
		case 2 : 
			System.out.println("List of Products");
			System.out.println(service.ListProduct());
			break;
			
		case 3 : 
			System.out.println("Search Product");
			
			try
			{
			System.out.println("Enter Product Id to be Searched");
			int searchid = Integer.parseInt(sc.next());
			System.out.println(service.searchProduct(searchid));
			}
			catch (InvalidProductIdException e1) {
				// TODO Auto-generated catch block
				System.out.println("Enter Valid Id");
			}
			catch (NumberFormatException e) {
				System.out.println("Enter Valid Product Id");
			}
			break;
			
		case 4 :
			try {
				System.out.println("Enter Product Id to be deleted");
				int delid = Integer.parseInt(sc.next());
				System.out.println(" Product Deleted " +service.deleteProduct(delid));
			} catch (InvalidProductIdException e1) {
				// TODO Auto-generated catch block
				System.out.println("Enter Valid Id");
			}
			catch (NumberFormatException e) {
				System.out.println("Enter Valid Product Id");
			}
			break;
			
		case 5 :
			System.out.println("Maxium Price Product");
			System.out.println(service.maxPriceProduct());
			break;
			
		case 6 :
			System.out.println("Minimum Price Product");
			System.out.println(service.minPriceProduct());
			break;
		case 7:
			System.out.println("Finding Products Within Range");
			System.out.println("Enter First Price of Product");
			int fromprice = sc.nextInt();
			System.out.println("Enter To Price Of Product");
			int toprice = sc.nextInt();
			//List<ProductInfo> products = service.salaryRange(fromprice, toprice);
			try {
				System.out.println("Products are : " +service.salaryRange(fromprice, toprice));
			} catch (InvalidPriceException e) {
				System.out.println("Pls Give Proper Prices");
			}
			break;
		case 8 :
			try
			{
			System.out.println("Update Product");
			System.out.println("Enter Product Id to be updated");
			int pudateid = sc.nextInt();
			System.out.println("Enter Product Name");
			String newname = sc.next();
			System.out.println("Enter Product Price");
			int newprice = sc.nextInt();
			System.out.println("Enter Product Vendor");
			String newvendor = sc.next();
			System.out.println("Enter Product Quantity");
			int newqty = sc.nextInt();
			ProductInfo newProdobj = new ProductInfo(pudateid, newname, newprice, newvendor, newqty);
			System.out.println("Updated Product is : "+service.updateProduct(pudateid, newProdobj));
			}
			catch (InputMismatchException e) {
				System.out.println("Enter Valid Product Details");
			} 
			break;
		case 9 :
				System.out.println("Avg Price of product");
				System.out.println(service.avgPriceProduct());
			break;
			
		default:
			int counter = ++count;
			if(counter==3)
			{
				System.out.println("Wrong Choices for 3 times Exiting the application pls try again");
				System.exit(0);
			}
			break;
		}
		}
	}
}
