package com.danIT.HW.Spring_Aplication.HW5.services;

import com.danIT.HW.Spring_Aplication.HW5.domain.Employer;

import java.util.List;

public interface EmployerService {


    Employer save(Employer employer);

    void delete(Employer employer);

    void deleteAll();

    void saveAll(List<Employer> entities);

    List<Employer> findAll();

    void deleteById(long id);

    Employer getOne(long id);
}
