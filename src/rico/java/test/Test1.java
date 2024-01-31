package test;

import context.ClassPathXmlApplicationContext;

public class Test1 {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
        AService aService = (AService) ctx.getBean("aservice");
        aService.sayHello();
    }
}
