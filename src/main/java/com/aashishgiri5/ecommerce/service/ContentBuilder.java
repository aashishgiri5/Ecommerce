package com.aashishgiri5.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class ContentBuilder {

    @Autowired
    private TemplateEngine templateEngine;


   public String sendContent(String body)
   {
       Context context=new Context();
       context.setVariable("message",body);
       String tempEngine = templateEngine.process("mailTemplate",context);
       return tempEngine;

   }
}
