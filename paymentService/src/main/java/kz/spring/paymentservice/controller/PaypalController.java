package kz.spring.paymentservice.controller;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import kz.spring.paymentservice.service.impl.IPaypalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@Controller
public class PaypalController {

    @Autowired
    IPaypalService iPaypalService;

    public static final String SUCCESS = "pay/success";
    public static final String CANCEL = "pay/cancel";


    @PostMapping("/pay/{price}")
    public String payment(@PathVariable("price") double price){
        try {
            Payment payment = iPaypalService.create(price, "KZT",
                    "PayPal", "sale", "payment desc",
                    "http://localhost:9090/" + CANCEL, "http://localhost:9090/" + SUCCESS);

            for(Links link:payment.getLinks()){
                if(link.getRel().equals("approval_url")){
                    return "Approval";
                }
            }
        } catch (PayPalRESTException e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return "payment";
    }

    @GetMapping(value = CANCEL)
    public String cancel(){
        return "cancel";
    }

    @GetMapping(value = SUCCESS)
    public String success(@RequestParam("paymentId") String paymentId, @RequestParam("PayerId") String payerId){

        try {
            Payment payment = iPaypalService.executePayment(paymentId, payerId);
            System.out.println(payment.toJSON());

            if(payment.getState().equals("approved")){
                return "success";
            }
        } catch (PayPalRESTException e) {
            System.out.println("log " + e.getMessage());
            e.printStackTrace();
        }
        return "success";
    }
}
