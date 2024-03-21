package com.tga.payment.repository;

import com.tga.payment.data.TransactionDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionDetailsRepository extends MongoRepository<TransactionDetails, Long> {

    TransactionDetails findByOrderId(long orderId);
}
