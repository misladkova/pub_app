package com.company;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    Collection<Order> findByDrinkId(int drink_id);
}
