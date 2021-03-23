package pl.training.shop.payments;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static java.lang.Long.parseLong;
import static org.junit.jupiter.api.Assertions.*;

class IncrementalPaymentIdGeneratorTest {

    private static final String ID_FORMAT = "\\d{10}";

    private final IncrementalPaymentIdGenerator paymentIdGenerator = new IncrementalPaymentIdGenerator();

    @DisplayName("Should generate valid id")
    @Test
    void shouldGenerateValidId(){
        String id = paymentIdGenerator.getNext();
        Assertions.assertTrue(id.matches(ID_FORMAT));
    }

    @DisplayName("Should generate id by incrementing value of previous one")
    @Test
    void shouldGenerateIdByIncrementingValueOfPreviousOne(){
        //given
        long firstId = parseLong(paymentIdGenerator.getNext());
        long secondId = parseLong(paymentIdGenerator.getNext());
        Assertions.assertEquals(firstId + 1, secondId);
    }


}
