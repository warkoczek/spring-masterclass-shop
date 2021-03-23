package pl.training.shop.payments;

import lombok.extern.java.Log;
import org.springframework.context.event.EventListener;


@Log
public class PaymentStatusChangedListener {

    @EventListener
    public void onPaymentStatusChange(PaymentStatusChangedEvent statusChangedEvent){
        log.info("Payment changed status: " + statusChangedEvent.getPayment());
    }
}
