package org.sikimaraservices.orders;

import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Orders,Long> {
    @Query("SELECT o FROM Orders o WHERE o.UserId = :userId")
    public List<Orders> findOrdersByUserId(@Param("userId") Long userId);
}
