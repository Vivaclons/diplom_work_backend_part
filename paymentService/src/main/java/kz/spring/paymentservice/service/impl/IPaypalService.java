package kz.spring.paymentservice.service.impl;

import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

public interface IPaypalService {
    Payment create(double total, String cur, String meth,
                   String goal, String desc, String cancel,
                   String success)  throws PayPalRESTException;

    Payment executePayment(String paymentId, String payerId)  throws PayPalRESTException;
}
