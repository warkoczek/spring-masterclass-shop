package pl.training.shop.payments;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

@RequiredArgsConstructor
public class HibernatePaymentRepository implements PaymentRepository {

    private final SessionFactory sessionFactory;

    @Override
    public Payment save(Payment payment) {
        Session session = sessionFactory.getCurrentSession();
        String id = (String) session.save(payment);
        payment.setId(id);
        return payment;
    }
}
