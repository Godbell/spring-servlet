package aop.main;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import aop.domain.Product;
import aop.service.ProductService;

public class App {
    public static void main(String[] args) {
        test01();
        test02();
    }

    public static void test01() {
        ConfigurableApplicationContext applicationContext
            = new ClassPathXmlApplicationContext("config/applicationContext.xml");
        ProductService productService = applicationContext.getBean(ProductService.class);

        Product tv = productService.find("TV");
        System.out.println(tv);

        applicationContext.close();
    }

    public static void test02() {
        ConfigurableApplicationContext applicationContext
            = new ClassPathXmlApplicationContext("config/applicationContext.xml");
        ProductService productService = applicationContext.getBean(ProductService.class);

        Product tv = productService.find("");
        System.out.println(tv);

        applicationContext.close();
    }
}
