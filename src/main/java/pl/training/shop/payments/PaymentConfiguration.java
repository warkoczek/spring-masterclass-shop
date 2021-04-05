package pl.training.shop.payments;

import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaymentConfiguration {

    @Bean
    public PaymentIdGenerator paymentIdGenerator(){
        return new IncrementalPaymentIdGenerator();
    }

    @Bean
    public PaymentRepository paymentRepository(SessionFactory sessionFactory){
        return new HibernatePaymentRepository(sessionFactory);
    }

    @Bean
    public PaymentService paymentService(PaymentIdGenerator paymentIdGenerator, PaymentRepository paymentRepository
    , ApplicationEventPublisher eventPublisher){
        return new FakePaymentService(paymentIdGenerator, paymentRepository, eventPublisher);
    }
    @Bean
    public PaymentConsoleLogger paymentConsoleLogger(MessageSource messageSource){
        return new PaymentConsoleLogger(messageSource);
    }
    @Bean
    public PaymentStatusChangedListener paymentStatusChangedListener(){
        return new PaymentStatusChangedListener();
    }
}
