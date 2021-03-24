package pl.training.shop.common.validator;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;

import java.util.stream.IntStream;

@Aspect
@RequiredArgsConstructor
public class ModelValidator {

    private final ValidatorService validatorService;

    @Before(value = "execution(* *(@Validate (*)))")
    public void validate(JoinPoint joinPoint){
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Object[] arguments = joinPoint.getArgs();
        IntStream.range(0, arguments.length).forEach(index -> validate(methodSignature, arguments[index], index));
    }

    private void validate(MethodSignature methodSignature, Object argument, int argumentIndex){

    }
}
