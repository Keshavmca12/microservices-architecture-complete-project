package org.pg.cartservice.adapters.out.repository;

import org.pg.cartservice.adapters.out.repository.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface OrderRepository extends JpaRepository<OrderEntity, String> {
    @Transactional
    @Modifying
    @Query(value="update orders o set o.order_status = :orderStatus where o.order_id = :orderId", nativeQuery = true)
    void updateOrderStatus(@Param("orderStatus")String orderStatus, @Param("orderId")String orderId);

}
