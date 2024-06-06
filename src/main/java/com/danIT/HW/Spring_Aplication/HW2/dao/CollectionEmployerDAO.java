package com.danIT.HW.Spring_Aplication.HW2.dao;

import com.danIT.HW.Spring_Aplication.HW2.domain.Customer;
import com.danIT.HW.Spring_Aplication.HW2.domain.Employer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
@RequiredArgsConstructor
public class CollectionEmployerDAO implements DAO<Employer>{


    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    @Transactional
    public Employer save(Employer employer) {
        if (employer.getId() == null) {
            entityManager.persist(employer);
        } else {
            entityManager.merge(employer);
        }
        return employer;
    }

    @Override
    @Transactional
    public boolean delete(Employer employer) {
        Employer searchEmployer = entityManager.find(Employer.class, employer.getId());
        if (searchEmployer != null) {
            entityManager.remove(searchEmployer);
            return true;
        } else {
            return false;
        }
    }

    @Override
    @Transactional
    public void deleteAll() {
        entityManager.createQuery("DELETE FROM Employer ").executeUpdate();
    }

    @Override
    @Transactional
    public void saveAll(List<Employer> entities) {
        entities.forEach(this::save);
    }

    @Override
    public List<Employer> findAll() {
        Query query = entityManager.createQuery("SELECT e FROM Employer e", Employer.class);
        return query.getResultList();
    }

    @Override
    public boolean deleteById(long id) {
        Employer searchEmployer = entityManager.find(Employer.class, id);
        if (searchEmployer != null) {
            entityManager.remove(searchEmployer);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Employer getOne(long id) {
        return entityManager.find(Employer.class, id);
    }
}
