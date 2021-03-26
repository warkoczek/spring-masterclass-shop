package pl.training.shop.payments;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.aspectj.lang.annotation.*;
import org.springframework.context.MessageSource;
import org.springframework.core.annotation.Order;

import java.util.Locale;

@Order(5)
@Aspect
@Log
@RequiredArgsConstructor
public class PaymentConsoleLogger {

    private static final String MESSAGE_KEY = "paymentInfo";

    private final MessageSource messageSource;

    public void logPayments(){
    }

    public void beforePayment(PaymentRequest paymentRequest){
        log.info("New payment request " + paymentRequest);
    }

    public void afterPayment(){
        log.info("After payment");
    }

    public void onException(RuntimeException exception){
        log.info("Payment exception " + exception.getClass().getSimpleName());
    }

    public void log(Payment payment){
        log.info(createLogEntry(payment));
    }

    private String createLogEntry(Payment payment){
        return messageSource.getMessage(MESSAGE_KEY, new String[] {payment.getMoney().toString()}, Locale.getDefault());
    }
}
