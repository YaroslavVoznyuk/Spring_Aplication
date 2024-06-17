package com.danIT.HW.Spring_Aplication.HW3.dao;


import com.danIT.HW.Spring_Aplication.HW3.domain.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CollectionCustomerJPA extends JpaRepository<Customer, Long> {
    @Query("SELECT c FROM Customer c WHERE c.id BETWEEN :from AND :to")
    Page<Customer> findAllInRange(@Param("from") Integer from, @Param("to") Integer to, Pageable pageable);
}
