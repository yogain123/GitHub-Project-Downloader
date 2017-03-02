package com.yogendra.controller;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.yogendra.model.Customer;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

@Controller
public class SimpleWebController {

	Logger log = LoggerFactory.getLogger(this.getClass());
	
    @RequestMapping(value="/form", method=RequestMethod.GET)
    public String customerForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "form";
    }

    @RequestMapping(value="/form", method=RequestMethod.POST)
    public String customerSubmit(@ModelAttribute Customer customer, Model model) {
    	
        model.addAttribute("customer", customer);
        String info = String.format("Customer Submission: id = %d, firstname = %s, lastname = %s", 
        								customer.getId(), customer.getFirstname(), customer.getLastname());
        log.info(customer.getFirstname());
        
//        
//        String ss = customer.getFirstname();
//        System.out.println(ss + " "+ss);
        
        
        //---------
        String s = customer.getFirstname();
        String arr[] = s.split("/");
        String gitHubName = arr[arr.length - 2];
        String projectName = arr[arr.length - 1];
        String pikachu = gitHubName+"/"+projectName;
        String dirName = "/Users/apple/Desktop/project";
        
        
        String url = "https://codeload.github.com/"+pikachu+"/zip/master";

        try {

       System.out.println("Downloading");

        saveFileFromUrlWithJavaIO(
        dirName + ".zip",
        url);

        System.out.println("Downloaded");

        } catch (MalformedURLException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        //--------      
        return "result";
    }

    public static void saveFileFromUrlWithJavaIO(String fileName, String fileUrl)
    		 throws MalformedURLException, IOException {
    		 BufferedInputStream in = null;
    		 FileOutputStream fout = null;
    		 try {
    		 in = new BufferedInputStream(new URL(fileUrl).openStream());
    		 fout = new FileOutputStream(fileName);

    		byte data[] = new byte[1024];
    		 int count;
    		 while ((count = in.read(data, 0, 1024)) != -1) {
    		 fout.write(data, 0, count);
    		 }
    		 } finally {
    		 if (in != null)
    		 in.close();
    		 if (fout != null)
    		 fout.close();
    		 }
    		 }
}