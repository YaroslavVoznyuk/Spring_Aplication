package com.danIT.HW.Spring_Aplication.HW4.dao;


import com.danIT.HW.Spring_Aplication.HW4.domain.Employer;
import org.springframework.data.jpa.repository.JpaRepository;



public interface CollectionEmployerJPA extends JpaRepository<Employer, Long> {

}
