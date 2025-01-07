package aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyAspect {
    @Before("execution(public aop.domain.Product aop.service.ProductService.find(String) throws RuntimeException)")
    public void adviceBefore() {
        System.out.println("--- BEFORE ADVICE ---");
    }

    @After("execution(public * aop.service.ProductService.find(..))")
    public void adviceAfter() {
        System.out.println("--- AFTER ADVICE ---");
    }

    @AfterReturning("execution(public * aop.service.ProductService.find(..))")
    public void adviceAfterReturning() {
        System.out.println("--- AFTER RETURNING ADVICE ---");
    }

    @AfterThrowing(value = "execution(public * *..*.ProductService.find(..))", throwing = "ex")
    public void adviceAfterThrowing(Throwable ex) {
        System.out.println("--- AFTER THROWING(" + ex.getMessage() + ") ADVICE ---");
    }

    @Around(value = "execution(* *..*.*.*(..))")
    public Object adviceAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("--- AROUND ADVICE BEFORE ---");

        Object result = proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());

        System.out.println("--- AROUND ADVICE AFTER ---");

        return result;
    }
}
