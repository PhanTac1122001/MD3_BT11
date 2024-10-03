package com.example.ss1b1.entity;

public class Product
{
	private Long id;
	private String name;
	private Double price;
	private String description;
	private String producer;
	private Boolean status;
	
	public Product()
	{
	}
	
	public Product(Long id, String name, Double price, String description, String producer, Boolean status)
	{
		this.id = id;
		this.name = name;
		this.price = price;
		this.description = description;
		this.producer = producer;
		this.status = status;
	}
	
	public Long getId()
	{
		return id;
	}
	
	public void setId(Long id)
	{
		this.id = id;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public Double getPrice()
	{
		return price;
	}
	
	public void setPrice(Double price)
	{
		this.price = price;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public void setDescription(String description)
	{
		this.description = description;
	}
	
	public String getProducer()
	{
		return producer;
	}
	
	public void setProducer(String producer)
	{
		this.producer = producer;
	}
	
	public  Boolean getStatus()
	{
		return status;
	}
	
	public void setStatus(Boolean status)
	{
		this.status = status;
	}
}
