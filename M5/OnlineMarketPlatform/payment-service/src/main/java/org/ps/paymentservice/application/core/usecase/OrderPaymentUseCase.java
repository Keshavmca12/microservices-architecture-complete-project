package org.ps.paymentservice.application.core.usecase;

import org.ps.paymentservice.application.core.domain.Item;
import org.ps.paymentservice.application.core.domain.Order;
import org.ps.paymentservice.application.core.domain.Payment;
import org.ps.paymentservice.application.core.domain.enums.OrderEventEnum;
import org.ps.paymentservice.application.core.domain.enums.PaymentStatusEnum;
import org.ps.paymentservice.application.ports.in.OrderPaymentInputPort;
import org.ps.paymentservice.application.ports.out.SavePaymentOutputPort;
import org.ps.paymentservice.application.ports.out.SendFailedPaymentOutputPort;
import org.ps.paymentservice.application.ports.out.SendValidatedPaymentOutputPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Component
public class OrderPaymentUseCase implements OrderPaymentInputPort {

    private Map<String, BigDecimal> userBalanceMap;

    @PostConstruct
    private void init() {
        this.userBalanceMap = new HashMap<>();
        this.userBalanceMap.put("111111111111", new BigDecimal(0));
        this.userBalanceMap.put("222222222222", new BigDecimal(1000));
        this.userBalanceMap.put("333333333333", new BigDecimal(20000));
        this.userBalanceMap.put("444444444444", new BigDecimal(1000000));
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderPaymentUseCase.class);

    private final SavePaymentOutputPort savePaymentOutputPort;
    private final SendValidatedPaymentOutputPort sendValidatedPaymentOutputPort;
    private final SendFailedPaymentOutputPort sendFailedPaymentOutputPort;

    public OrderPaymentUseCase(SavePaymentOutputPort savePaymentOutputPort, SendValidatedPaymentOutputPort sendValidatedPaymentOutputPort, SendFailedPaymentOutputPort sendFailedPaymentOutputPort) {
        this.savePaymentOutputPort = savePaymentOutputPort;
        this.sendValidatedPaymentOutputPort = sendValidatedPaymentOutputPort;
        this.sendFailedPaymentOutputPort = sendFailedPaymentOutputPort;
    }


    @Override
    public void execute(final Order order) {
        BigDecimal totalPrice = order.getItems().stream().map(x -> x.getSubTotal()).reduce(BigDecimal.ZERO, BigDecimal::add);      // reduce

        try {
            BigDecimal balance = this.userBalanceMap.getOrDefault(order.getPaymentDetails().getAccountno(), null);
            if (balance.compareTo(totalPrice) < 0) {
                throw new RuntimeException("Insufficient balance");
            }
            this.userBalanceMap.put(order.getPaymentDetails().getAccountno(), balance.subtract(totalPrice));
            Payment payment = createPayment(order, totalPrice);
            payment.setPaymentStatus(PaymentStatusEnum.PAYMENT_APPROVED);
            this.savePaymentOutputPort.execute(payment);
            this.sendValidatedPaymentOutputPort.execute(order, OrderEventEnum.VALIDATED_PAYMENT);

        } catch (final Exception ex) {
            Payment payment = createPayment(order, totalPrice);
            payment.setPaymentStatus(PaymentStatusEnum.PAYMENT_REJECTED);
            this.savePaymentOutputPort.execute(payment);
            LOGGER.error("OrderPaymentUseCase -  Payment error [{}]", ex.getMessage());
            this.sendFailedPaymentOutputPort.execute(order, OrderEventEnum.FAILED_PAYMENT);
        }
    }

    private Payment createPayment(final Order order, final BigDecimal totalPrice) {
        //return new Payment(null, order.getUserId(), order.getId(), new BigDecimal(order.getItems().stream().mapToDouble(Item::getPrice).sum()));
        return Payment.builder().orderId(order.getId())
                .userId(order.getUserId())
                .value(totalPrice)
                .paymentDetails(order.getPaymentDetails()).build();
    }
}
