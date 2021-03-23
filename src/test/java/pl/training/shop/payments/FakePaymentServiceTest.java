package pl.training.shop.payments;

import org.javamoney.moneta.FastMoney;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class FakePaymentServiceTest {
    private static final String PAYMENT_ID = "1";
    private static final FastMoney MONEY = LocalMoney.of(1_000);
    private static final PaymentRequest PAYMENT_REQUEST = PaymentRequest.builder().money(MONEY).build();
    @Mock
    private UUIDPaymentIdGenerator paymentIdGenerator;
    @Mock
    private PaymentRepository paymentRepository;
    @Mock
    private ApplicationEventPublisher eventPublisher;
    private Payment payment;

    @BeforeEach
    void setUp(){
        Mockito.when(paymentIdGenerator.getNext()).thenReturn(PAYMENT_ID);
        Mockito.when(paymentRepository.save(any(Payment.class))).then(returnsFirstArg());
        FakePaymentService paymentService = new FakePaymentService(paymentIdGenerator, paymentRepository, eventPublisher);
        //Why can not declare like this Payment payment = paymentService.process(PAYMENT_REQUEST);
        payment = paymentService.process(PAYMENT_REQUEST);
    }

    @DisplayName("Should assign generated id to created payment")
    @Test
    void shouldAssignGeneratedIdToCreatedPayment(){
        assertEquals(PAYMENT_ID, payment.getId());
    }

    @DisplayName("Should assign money from payment request to created payment")
    @Test
    void shouldAssignMoneyFromPaymentRequestToCreatedPayment(){
        assertEquals(MONEY, payment.getMoney());
    }

    @DisplayName("Should assign timestamp to created payment")
    @Test
    void shouldAssignTimestampToCreatedPayment(){
        assertNotNull(payment.getTimestamp());
    }
    @DisplayName("Should assign payment status started to created payment")
    @Test
    void shouldAssignPaymentStatusAsStartedToCreatedPayment(){
        assertEquals(PaymentStatus.STARTED, payment.getStatus());
    }
    @DisplayName("Should save created payment")
    @Test
    void shouldSaveCreatedPayment(){
        verify(paymentRepository).save(payment);
    }


}
