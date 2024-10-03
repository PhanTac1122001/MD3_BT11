package com.example.ss1b1.controller;

import com.ra.session11md3.entity.Product;
import com.ra.session11md3.service.IProductService;
import com.ra.session11md3.service.impl.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductController", value = "/productController")
public class ProductController extends HttpServlet
{
	
	IProductService productService = new ProductServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		String action = req.getParameter("action");
		System.out.println("action = " + action);
		if (action == null)
		{
			action = "";
		}
		switch (action)
		{
			case "VIEWADD":
				req.getRequestDispatcher("/products/add_product.jsp").forward(req, resp);
				break;
			case "DELETE":
			{
				Long id = Long.parseLong(req.getParameter("id"));
				productService.deleteById(id);
				listProduct(req, resp);
				break;
			}
			case "VIEWEDIT":
			{
				Long id = Long.parseLong(req.getParameter("id"));
				Product product = productService.findById(id);
				req.setAttribute("product", product);
				req.getRequestDispatcher("/products/edit_product.jsp").forward(req, resp);
				break;
			}
			default:
				listProduct(req, resp);
		}
		
	}
	
	public void listProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		List<Product> products = ProductServiceImpl.products;
		req.setAttribute("products", products);
		req.getRequestDispatcher("/products/products.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		if (action == null)
		{
			action = "";
		}
		switch (action)
		{
			case "HANDLEADD":
			{
				String name = req.getParameter("name");
				String description = req.getParameter("description");
				Double price = Double.parseDouble(req.getParameter("price"));
				String producer = req.getParameter("producer");
				Boolean status = Boolean.parseBoolean(req.getParameter("status"));
				Product product = new Product();
				product.setName(name);
				product.setDescription(description);
				product.setPrice(price);
				product.setProducer(producer);
				product.setStatus(status);
				productService.addProduct(product);
				// c1: gọi lại listProduct để nó hiển thị
//				listProduct(req, resp);
				// c2: sendRedirect là method get
				resp.sendRedirect("/productController");
				break;
			}
			case "HANDLEUPDATE":
			{
				Long id = Long.parseLong(req.getParameter("id"));
				String name = req.getParameter("name");
				String description = req.getParameter("description");
				Double price = Double.parseDouble(req.getParameter("price"));
				String producer = req.getParameter("producer");
				Boolean status = Boolean.parseBoolean(req.getParameter("status"));
				Product product = new Product();
				product.setId(id);
				product.setName(name);
				product.setDescription(description);
				product.setPrice(price);
				product.setProducer(producer);
				product.setStatus(status);
				productService.updateProduct(product);
				// c1: gọi lại listProduct để nó hiển thị
//				listProduct(req, resp);
				// c2: sendRedirect là method get
				resp.sendRedirect("/productController");
				break;
			}
			case "SEARCH":
			{
				String search = req.getParameter("search");
				List<Product> result = productService.searchProduct(search);
				req.setAttribute("products", result);
				req.getRequestDispatcher("/products/products.jsp").forward(req, resp);
				break;
			}
			default:
				listProduct(req, resp);
		}
	}
}
