package com.danIT.HW.Spring_Aplication.HW5.dao;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CollectionCustomerJPA extends JpaRepository<com.danIT.HW.Spring_Aplication.HW5.domain.Customer, Long> {
    @Query("SELECT c FROM Customer c WHERE c.id BETWEEN :from AND :to")
    Page<com.danIT.HW.Spring_Aplication.HW5.domain.Customer> findAllInRange(@Param("from") Integer from, @Param("to") Integer to, Pageable pageable);
}
