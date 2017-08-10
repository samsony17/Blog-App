package com.mongodb;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.log4j.BasicConfigurator;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

import java.io.StringWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.halt;
import static spark.Spark.port;

public class HelloWorldSpark {
    public static void main(String[] args) {
        BasicConfigurator.configure();
        //port(8081);
        final Configuration config = new Configuration();
        config.setClassForTemplateLoading(HelloWorldSpark.class,"/");
        Spark.get("/", new  Route()
        {
            StringWriter writer = new StringWriter();

            public Object handle(Request request, Response response) throws Exception {
                try{
                    Template hellotemplate = config.getTemplate("index.html");

                    Map<String, Object> hellomap = new HashMap<String, Object>();
                    hellomap.put("fruits", Arrays.asList("Mango","Apple","Banana","Orange","Berries"));
                    hellotemplate.process(hellomap, writer);
                }
                catch(Exception e)
                {
                    halt(500);
                    System.out.println(e.toString());
                }
                return writer;

            }



        });

        Spark.post("/favourite_fruit", new Route()
        {

            public Object handle(Request request, Response response) throws Exception {
                final String fruit = request.queryParams("fruit");
                if(fruit == null)
                {
                    return "Why dont you pick one fruit";
                }
                else
                {
                    return "Your favourite fruit is " + fruit;
                }
            }
        });
    }
}
