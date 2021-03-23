package pl.training.shop.payments;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Scope;

@EnableAspectJAutoProxy
@Configuration
public class PaymentConfiguration {

    @Scope(BeanDefinition.SCOPE_SINGLETON)
    @Bean("paymentIdGenerator")
    public PaymentIdGenerator incrementalPaymentIdGenerator(){
        return new IncrementalPaymentIdGenerator();
    }

    @Bean
    public PaymentIdGenerator uuidPaymentIdGenerator(){
        return new IncrementalPaymentIdGenerator();
    }

    @Bean
    public PaymentRepository paymentRepository(){
        return new HashMapPaymentRepository();
    }

    @Bean(initMethod = "init", destroyMethod = "destroy")
    public PaymentService fakePaymentService(PaymentIdGenerator paymentIdGenerator, PaymentRepository paymentRepository
    , ApplicationEventPublisher eventPublisher){
        return new FakePaymentService(paymentIdGenerator, paymentRepository, eventPublisher);
    }
    @Bean
    public PaymentConsoleLogger paymentConsoleLogger(MessageSource messageSource){
        return new PaymentConsoleLogger(messageSource);
    }
}
