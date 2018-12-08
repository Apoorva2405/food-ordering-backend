package org.upgrad.services;

import org.springframework.stereotype.Service;
import org.upgrad.models.Payment;
import org.upgrad.repositories.PaymentRepository;

import javax.transaction.Transactional;

/*
    This class contains implementation of all payment related methods.
 */

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService{

    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    // This method is used to get all the payment methods from the database
    @Override
    public Iterable<Payment> getPaymentMethods(){
        return paymentRepository.getPaymentDetails();
    }
}
