package com.example.springboothelloworld.controller;
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.RestController;  
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;

@RestController  
public class HelloWorldController   
{  

	@Autowired
    private Environment env;

    @RequestMapping("/")  
    public String hello()   
    {  
      return "Hello World " + env.getProperty("STACK");  
    }  
}  

