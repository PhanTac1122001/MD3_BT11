package com.example.ss11b2.controller;

import com.example.ss11b2.entity.Category;
import com.example.ss11b2.sevice.ICategorySevice;
import com.example.ss11b2.sevice.impl.CategoryImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/category")
public class CategoryController extends HttpServlet {

    ICategorySevice categorySevice= new CategoryImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String action=req.getParameter("action");
        System.out.println("action"+action);
        if(action==null){
            action="";
        }
        switch (action){
            case "VIEWADD":
                req.getRequestDispatcher("/categorys/add_category.jsp").forward(req,resp);
                break;
            case "VIEWEDIT":
            {
                int id=Integer.parseInt(req.getParameter("id"));
                Category category=categorySevice.findById(id);
                req.setAttribute("category",category);
                req.getRequestDispatcher("/categorys/update_category.jsp").forward(req,resp);
                break;
            }
            case "DELETE":
            {
                int id=Integer.parseInt(req.getParameter("id"));
                categorySevice.deleteById(id);
                listCategory(req, resp);
                break;
            }
            default:
                listCategory(req,resp);
        }


    }
    public void listCategory(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{
        List<Category> categories=CategoryImpl.categories;
        req.setAttribute("categories",categories);
        req.getRequestDispatcher("/categorys/category.jsp").forward(req,resp);
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
                Boolean status=Boolean.parseBoolean(req.getParameter("status"));
                Category category=new Category();
                category.setName(name);
                category.setStatus(status);
                categorySevice.addCategory(category);
                resp.sendRedirect("/category");
                break;
            }
            case "UPDATE":
            {
                int id=Integer.parseInt(req.getParameter("id"));
                String name=req.getParameter("name");
                Boolean status=Boolean.parseBoolean(req.getParameter("status"));
                Category category=new Category();
                category.setId(id);
                category.setName(name);
                category.setStatus(status);
                categorySevice.updateCategory(category);
                resp.sendRedirect("/category");
                break;
            }
            default:
                listCategory(req,resp);
        }
    }
}
