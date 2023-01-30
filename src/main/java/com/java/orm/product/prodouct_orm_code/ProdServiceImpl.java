package com.java.orm.product.prodouct_orm_code;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;

import com.java.user.exception.DuplicateProductException;
import com.java.user.exception.InvalidPriceException;
import com.java.user.exception.InvalidProductIdException;
import com.java.user.exception.NegativePriceException;

public class ProdServiceImpl implements ProductService{
	
	static SessionFactory factory = null;
	public ProdServiceImpl() {
		if(factory==null)
		{
			Configuration config = new Configuration();
			config.configure("hibernate.cfg.xml");
			factory = config.buildSessionFactory();
			System.out.println("Session Factory Created");
		}
		else
		{
			System.out.println("Session Factory already initilized..............");
		}
	}
	public void cleanup(Session session, Transaction tr)
	{
		if(session!=null)
		{
			if(tr!=null)
			{
				session.flush();
				tr.commit();
			}
			session.close();
		}
	}
	public String addProduct(ProductInfo prod) throws DuplicateProductException, InvalidProductIdException{
		Session session = null;
		Transaction tr = null;
		if(prod.getProd_Id()<0)
		{
			throw new InvalidProductIdException("Enter Valid Id");
		}
		try
		{
			session = factory.openSession();
			tr = session.beginTransaction();
			ProductInfo product = session.get(ProductInfo.class, prod.getProd_Id());
			if(product!=null && prod!=null && prod.getProd_Id()==product.getProd_Id())
				{throw new DuplicateProductException("Duplicate Product");}
			else if(prod!=null && prod.getProd_price()<0)
			{
				throw new NegativePriceException("Price Can not be negative");
			}
			else 
			{
			session.save(prod);
			System.out.println("Product Added.....");
			}
		
		}
		catch (NegativePriceException e) {
			System.out.println("Price Cannot be Negative");
		}
		catch (DuplicateProductException e) {
			System.out.println("Duplicate Product");
		}
		catch (Exception e) {
			System.out.println("Problem Adding in Product");
		}
		finally
		{
			cleanup(session,tr);
		}
		return "Product not added..";
	}
	public List<ProductInfo> ListProduct() {
		Session session = null;
		Transaction tr = null;
		try
		{
			session = factory.openSession();
			Criteria crt1 = session.createCriteria(ProductInfo.class);
			List<ProductInfo> products = (List<ProductInfo>) crt1.list();
			System.out.println(products);
			//return products;
		}
		catch (Exception e) {
			System.out.println("Problem in List Product");
		}
		finally {
			cleanup(session, tr);
		}
		return null;
	}
	public ProductInfo searchProduct(int prodId) throws InvalidProductIdException{
		Session session = null;
		Transaction tr = null;
		try
		{
			session = factory.openSession();
			if(prodId<0)
			{
				throw new InvalidProductIdException("Enter Valid Id");
			}
			else
			{
			ProductInfo product = session.get(ProductInfo.class, prodId);
			if(product==null)
			{
				System.out.println("Product not Found" +prodId);
			}
			return product;
			}
		}
		catch (InvalidProductIdException e) {
			System.out.println("Enter Valid ID");
		}
		catch (Exception e) {
			System.out.println("Problem Occur in Searching Product");
		}
		finally {
			cleanup(session, tr);
		}
		return null;
	}
	public boolean deleteProduct(int prodId) throws InvalidProductIdException{
		Session session = null;
		Transaction tr = null;
		try
		{
			session = factory.openSession();
			ProductInfo deleteprod = session.get(ProductInfo.class, prodId);
			if(prodId<0)
			{
				throw new InvalidProductIdException("Enter Valid Id");
			}
			else if(deleteprod!=null)
			{
				tr = session.beginTransaction();
				session.delete(deleteprod);
				return true;
			}
			else if (deleteprod==null) {
				System.out.println("Product Doesnot exits" +prodId);
			}
		}
		catch (InvalidProductIdException e) {
			System.out.println("Enter Valid Id");
		}
		catch (Exception e) {
			System.out.println("Problem occur in deleting");
		}
		finally
		{
			cleanup(session, tr);
		}
		return false;
	}
	public ProductInfo maxPriceProduct() {
		Session session = null;
		Transaction tr = null;
		try
		{
			session = factory.openSession();
			tr = session.beginTransaction();
			
//			String maxquery = "select max(p.product_price) from product_table p";
//			Query query = session.createSQLQuery(maxquery);
//			System.out.println("Maximum Price of Product is : " +query.list().get(0));
			
//			Criteria crit = session.createCriteria(ProductInfo.class);
//			crit.addOrder(Order.desc("prod_price"));
//			crit.setMaxResults(1);
//			maxprod = (ProductInfo) crit.uniqueResult();
//			System.out.println("Max Product is : " +maxprod);
			
			Criteria cr = session.createCriteria(ProductInfo.class);
			cr.setProjection(Projections.max("prod_price"));
			int maxpr = (Integer) cr.list().get(0);
			System.out.println(maxpr);
			Criteria cr1 = session.createCriteria(ProductInfo.class);
			cr1.add(Restrictions.eq("prod_price", maxpr));
			return (ProductInfo) cr1.list().get(0);
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("Problem Finding in Product");
			return null;
		}
		
		finally
		{
			cleanup(session, tr);
			
		}
		//return maxprod;
	}
	public ProductInfo minPriceProduct() {
		Session session = null;
		Transaction tr = null;
		ProductInfo minprod = null;
		try
		{
			session = factory.openSession();
			tr = session.beginTransaction();
			
//			String maxquery = "select min(p.product_price) from product_table p";
//			Query query = session.createSQLQuery(maxquery);
//			System.out.println("Minimum Price of Product is : " +query.list().get(0));
			
//			Criteria crit = session.createCriteria(ProductInfo.class);
//			crit.addOrder(Order.asc("prod_price"));
//			crit.setMaxResults(1);
//			minprod = (ProductInfo) crit.uniqueResult();
//			System.out.println("Max Product is : " +minprod);
			
			Criteria cr = session.createCriteria(ProductInfo.class);
			cr.setProjection(Projections.min("prod_price"));
			int minprice = (Integer) cr.list().get(0);
			System.out.println(minprice);
			Criteria cr1 = session.createCriteria(ProductInfo.class);
			cr1.add(Restrictions.eq("prod_price", minprice));
			return (ProductInfo) cr1.list().get(0);
			
		}
		catch (Exception e) {
			System.out.println("Problem Finding in Product");
			return null;
		}
		
		finally
		{
			cleanup(session, tr);
		}
		//return minprod;
	}
	public List<ProductInfo> salaryRange(int price1, double price2) throws InvalidPriceException{
		Session session = null;
		Transaction tr = null;
		try
		{
			session = factory.openSession();
			tr = session.beginTransaction();
			if(price1>=price2)
			{ throw new InvalidPriceException("Invalid Price Range");}
			
//			String maxquery = "select * from product_table p where(p.product_price)>=5000 and (p.product_price)<=25000;";
//			Query query = session.createSQLQuery(maxquery);
//			System.out.println("Products are : " +query.getResultList());
			
			Criteria cr = session.createCriteria(ProductInfo.class);
			cr.add(Restrictions.between("prod_price", 20000, 60000));
			List<ProductInfo> prodlist = cr.list();
			System.out.println(prodlist);
		}
		catch (InvalidPriceException e) {
			System.out.println("Pls Specify Proper Price range");
		}
		catch (Exception e) {
			System.out.println("Problem Finding in Product");
		}
		
		finally
		{
			cleanup(session, tr);
		}
		return null;
	}
	public ProductInfo updateProduct(int pid, ProductInfo product) {
		Session session = null;
		Transaction tr = null;
		try
		{
			session = factory.openSession();
			ProductInfo updatprod = session.get(ProductInfo.class, pid);//select * from product_tble where pid=...
			if(product!=null && product.getProd_price()<0)
			{
				throw new NegativePriceException("Price Can not be negative");
			}
			else if(updatprod!=null)
			{
				tr = session.beginTransaction();
				updatprod.setProd_Name(product.getProd_Name());
				updatprod.setProd_price(product.getProd_price());
				updatprod.setProd_qty(product.getProd_qty());
				updatprod.setProd_vendor(product.getProd_vendor());
				session.update(updatprod);
				System.out.println("Product Updated.....");
				return updatprod;
			}
		}
		catch (NegativePriceException e) {
			System.out.println("Price can not be negative");
		}
		catch (Exception e) {
			System.out.println("Problem Updating in Product");
		}
		finally
		{
			cleanup(session,tr);
		}
		return null;
	}
	public double avgPriceProduct() {
		Session session = null;
		Transaction tr = null;
		try
		{
			session = factory.openSession();
			Criteria cr = session.createCriteria(ProductInfo.class);
			cr.setProjection(Projections.avg("prod_price"));
			double avg = (Double) cr.list().get(0);
			System.out.println("Avg is : " +avg);
			return avg;
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("Can not find avg");
			return 0.0;
		}
		finally
		{
			cleanup(session, tr);
			
		}
		
	}

}
