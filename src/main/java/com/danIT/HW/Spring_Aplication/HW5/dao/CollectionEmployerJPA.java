package com.danIT.HW.Spring_Aplication.HW5.dao;


import com.danIT.HW.Spring_Aplication.HW5.domain.Employer;
import org.springframework.data.jpa.repository.JpaRepository;



public interface CollectionEmployerJPA extends JpaRepository<Employer, Long> {

}
