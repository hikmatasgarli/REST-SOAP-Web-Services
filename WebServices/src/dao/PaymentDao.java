package dao;


import model.AdvancedSearch;
import model.Payment;

import java.util.List;

public interface PaymentDao {
    List<Payment> getPaymentList() throws Exception;

    boolean addPayment(Payment payment) throws Exception;

    Payment getPaymentById(Long paymentId) throws Exception;

    boolean updatePayment(Payment payment, Long paymentId) throws Exception;

    List<Payment> advancedSearchPaymentData(AdvancedSearch advancedSearch) throws Exception;

    boolean deletePayment(Long paymentId) throws Exception;


}
