package com.tga.search.handler;

import com.tga.eventsource.handler.EventListener;
import com.tga.order.event.*;
import com.tga.search.data.es.customer.ESCustomer;
import com.tga.search.data.es.customer.Item;
import com.tga.search.data.es.customer.Order;
import com.tga.search.data.es.customer.OrderStatusEnum;
import com.tga.search.data.es.product.Model;
import com.tga.search.data.es.product.Product;
import com.tga.search.data.es.repository.CustomerRepository;
import com.tga.search.data.es.product.ESProduct;
import com.tga.search.data.es.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.HashSet;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Component("Order")
public class OrderEventListener implements EventListener {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;


    /**
     *
     * @param event
     */
    public void handle(OrderCreatedEvent event) {
        log.info("OrderEventListener: OrderCreatedEvent");
        updateCustomerOrder(event.getOrder());
    }

    /**
     *
     * @param event
     */
    public void handle(OrderPaymentSuccessEvent event) {
        log.info("OrderEventListener: OrderPaymentSuccessEvent");
        updateCustomerOrder(event.getOrder());
    }

    /**
     *
     * @param event
     */
    public void handle(OrderPlacedEvent event) {
        log.info("OrderEventListener: OrderPlacedEvent");
        updateCustomerOrder(event.getOrder());
    }

    /**
     *
     * @param event
     */
    public void handle(OrderOnHoldEvent event) {
        log.info("OrderEventListener: OrderOnHoldEvent");
        updateCustomerOrder(event.getOrder());
    }

    /**
     *
     * @param event
     */
    public void handle(OrderDeliveredEvent event) {
        log.info("OrderEventListener: OrderDeliveredEvent");
        updateCustomerOrder(event.getOrder());
    }

    /**
     *
     * @param event
     */
    public void handle(OrderReturnedEvent event) {
        log.info("OrderEventListener: OrderReturnedEvent");
        updateCustomerOrder(event.getOrder());
    }

    /**
     *
     * @param event
     */
    public void handle(OrderCancelledEvent event) {
        log.info("OrderEventListener: OrderCancelledEvent");
        updateCustomerOrder(event.getOrder());
    }

    /**
     *
     * @param order
     */
    private void updateCustomerOrder(com.tga.order.model.Order order) {
        Optional<ESCustomer> esCustomerOpt = customerRepository.findById(order.getCustomerId());
        ESCustomer esCustomer = null;
        if(esCustomerOpt.isPresent()){
            esCustomer = esCustomerOpt.get();
        }else{
            esCustomer = new ESCustomer();
        }
        esCustomer.setId(order.getCustomerId());
        Order esOrder = new Order();
        if(esCustomer.getOrders() == null ||  esCustomer.getOrders().isEmpty()){
            esCustomer.setOrders(new HashSet<>());
        }else{
            for(Order o: esCustomer.getOrders()){
                if(order.getId().equals(o.getId())){
                    esOrder = o;
                    break;
                }
            }
        }
        esCustomer.getOrders().add(getOrder(esOrder, order));
        customerRepository.save(esCustomer);
    }

    /**
     *
     * @param esOrder
     * @param order
     * @return
     */
    private Order getOrder(Order esOrder, com.tga.order.model.Order order) {
        esOrder.setId(order.getId());
        esOrder.setCustomerId(order.getCustomerId());
        esOrder.setCartId(order.getCartId());

        if(order.getItems() != null) {
            esOrder.setItems(order.getItems().stream().map(item -> {
                ESProduct esProduct = productRepository.findById(item.getProductId()).get();
                Product product = new Product(esProduct.getId(), esProduct.getName(), esProduct.isPublished(),
                        esProduct.getCategoryId(), esProduct.getCategoryName(),
                        esProduct.getModels().stream().filter(m-> m.getSku().equals(item.getSku())).map(m -> new Model(m.getSku(), m.getName(), m.getPrice())).collect(Collectors.toList()));
                return new Item(product, item.getUnit(), item.getPrice());
            }).collect(Collectors.toList()));
        }
        esOrder.setStatus(OrderStatusEnum.valueOf(order.getStatus().name()));
        esOrder.setTotalPrice(order.getTotalPrice());
        esOrder.setAddressId(order.getAddressId());
        esOrder.setComments(order.getComments());
        return esOrder;
    }
}
