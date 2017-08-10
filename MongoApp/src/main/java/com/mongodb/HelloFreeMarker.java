package com.mongodb;

import freemarker.template.Configuration;
import freemarker.template.Template;
import java.util.*;

import java.io.StringWriter;

public class HelloFreeMarker {
    public static void main(String[] args) {
        Configuration config = new Configuration();
        config.setClassForTemplateLoading(HelloFreeMarker.class,"/");
   try{
           Template hellotemplate = config.getTemplate("index.html");
       StringWriter writer = new StringWriter();
       Map<String, Object> hellomap = new HashMap<String, Object>();
       hellomap.put("name","samson");
       hellotemplate.process(hellomap, writer);
   }
   catch(Exception e)
        {
            System.out.println(e.toString());
        }


    }
}
