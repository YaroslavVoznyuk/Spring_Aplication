package com.danIT.HW.Spring_Aplication.HW4.services;

import com.danIT.HW.Spring_Aplication.HW4.dao.CollectionEmployerJPA;
import com.danIT.HW.Spring_Aplication.HW4.domain.Employer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class DefaultEmployerService implements EmployerService {

    private CollectionEmployerJPA employerDAO;

    @Autowired
    public DefaultEmployerService(CollectionEmployerJPA employerDAO) {
        this.employerDAO = employerDAO;
    }

    @Override
    public Employer save(Employer employer) {
        return employerDAO.save(employer);
    }

    @Override
    public void delete(Employer employer) {
         employerDAO.delete(employer);
    }

    @Override
    public void deleteAll() {
        employerDAO.deleteAll();
    }

    @Override
    public void saveAll(List<Employer> entities) {
        employerDAO.saveAll(entities);
    }

    @Override
    public List<Employer> findAll() {
        return employerDAO.findAll();
    }

    @Override
    public void deleteById(long id) {
         employerDAO.deleteById(id);
    }

    @Override
    public Employer getOne(long id) {
        return employerDAO.getOne(id);
    }
}
