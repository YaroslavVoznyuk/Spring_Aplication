package com.danIT.HW.Spring_Aplication.HW2.services;

import com.danIT.HW.Spring_Aplication.HW2.domain.Employer;

import java.util.List;

public interface EmployerService {


    Employer save(Employer employer);

    boolean delete(Employer employer);

    void deleteAll();

    void saveAll(List<Employer> entities);

    List<Employer> findAll();

    boolean deleteById(long id);

    Employer getOne(long id);
}
