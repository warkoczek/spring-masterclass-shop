package pl.training.shop.payments;



import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.time.Instant;
@Log
@RequiredArgsConstructor
@Service
public class FakePaymentService implements PaymentService {

    private final PaymentIdGenerator paymentIdGenerator;
    private final PaymentRepository paymentRepository;
    private final ApplicationEventPublisher eventPublisher;

    @LogPayments
    @Override
    public Payment process(PaymentRequest paymentRequest){
        var payment =  Payment.builder()
                .id(paymentIdGenerator.getNext())
                .money(paymentRequest.getMoney())
                .timestamp(Instant.now())
                .status(PaymentStatus.STARTED)
                .build();
        eventPublisher.publishEvent(new PaymentStatusChangedEvent(this, payment));
        return paymentRepository.save(payment);

    }
    public void init(){
        log.info("Fake Payment Service is initialized");
    }
    public void destroy(){
        log.info("Fake Payment Service is going down");
    }


}
