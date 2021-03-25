package pl.training.shop.common.validator;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.IntStream;

@Aspect
@RequiredArgsConstructor
public class ModelValidator {

    private final ValidatorService validatorService;

    @Before("execution(* *(@Validate (*)))")
    public void validate(JoinPoint joinPoint){
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Object[] arguments = joinPoint.getArgs();
        IntStream.range(0, arguments.length)
                .forEach(index -> validate(methodSignature, arguments[index], index));
    }

    private void validate(MethodSignature methodSignature, Object argument, int argumentIndex){
        Annotation[] annotations = getAnnotations(methodSignature, argumentIndex);
        Optional<Validate> validateAnnotation = getValidateAnnotation(annotations);
        validateAnnotation.ifPresent(validate -> validatorService.validate(argument, validate.exception()));
    }
    private Annotation[] getAnnotations(MethodSignature methodSignature, int argumentIndex){
        return methodSignature.getMethod().getParameterAnnotations()[argumentIndex];
    }
    private Optional<Validate> getValidateAnnotation(Annotation[] annotations){
        return Arrays.stream(annotations)
                .filter(annotation -> annotation instanceof Validate)
                .map(annotation -> (Validate) annotation)
                .findFirst();
    }
}
