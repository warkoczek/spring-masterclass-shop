package pl.training.shop.common.retry;

import lombok.Setter;
import lombok.extern.java.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
@Log
public class MethodExecutor {

    @Setter
    private int attempts = 3;

    @Around("@annotation(Retry)")
    public Object execute(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
       int currentAttempt = 0;
       Throwable throwable;
       do{
           currentAttempt++;
           log.info(String.format("%s execution attempt %d", proceedingJoinPoint.getSignature().getName(), currentAttempt));
           try{
               return proceedingJoinPoint.proceed();

           }catch(Throwable t){
                throwable = t;
           }

       }while (currentAttempt < attempts);
        throw throwable;
    }

}
