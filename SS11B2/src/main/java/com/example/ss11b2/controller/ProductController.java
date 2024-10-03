package com.example.ss11b2.controller;

import com.example.ss11b2.entity.Category;
import com.example.ss11b2.entity.Product;
import com.example.ss11b2.sevice.ICategorySevice;
import com.example.ss11b2.sevice.IProductSevice;
import com.example.ss11b2.sevice.impl.CategoryImpl;
import com.example.ss11b2.sevice.impl.ProductImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/product")
public class ProductController extends HttpServlet {
    IProductSevice productSevice=new ProductImpl();
    ICategorySevice categorySevice=new CategoryImpl();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String action=req.getParameter("action");
    if(action==null){
        action="";
    }
    switch (action){
        case "VIEWADD":
            req.setAttribute("categories",CategoryImpl.categories);
            req.getRequestDispatcher("/products/add_product.jsp").forward(req,resp);
        default:
            listProduct(req,resp);
    }
    }
    public void listProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        List<Product> products=ProductImpl.products;
        req.setAttribute("products",products);
        req.getRequestDispatcher("/products/product.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action=req.getParameter("action");
        if(action==null){
            action="";
        }
        switch (action){
            case "ADD":
            {
                String name=req.getParameter("name");
                int price=Integer.parseInt(req.getParameter("price"));
                int stock=Integer.parseInt(req.getParameter("stock"));
                Category category =categorySevice.findById(Integer.parseInt(req.getParameter("category")));
                Boolean status=Boolean.parseBoolean(req.getParameter("status"));
                Product product=new Product();
                product.setName(name);
                product.setStatus(status);
                product.setPrice(price);
                product.setStock(stock);
                product.setCategory(category);
                productSevice.addProduct(product);
                resp.sendRedirect("/product");
                break;
            }
            case "UPDATE":
            {
                int id=Integer.parseInt(req.getParameter("id"));
                String name=req.getParameter("name");
                Boolean status=Boolean.parseBoolean(req.getParameter("status"));
                Product product=new Product();
                product.setId(id);
                product.setName(name);
                product.setStatus(status);
                productSevice.updateCategory(product);
                resp.sendRedirect("/category");
                break;
            }
            default:
                listProduct(req,resp);
        }
    }

}
