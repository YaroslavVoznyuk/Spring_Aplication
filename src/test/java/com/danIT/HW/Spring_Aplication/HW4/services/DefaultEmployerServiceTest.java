package com.danIT.HW.Spring_Aplication.HW4.services;

import com.danIT.HW.Spring_Aplication.HW4.dao.CollectionEmployerJPA;
import com.danIT.HW.Spring_Aplication.HW4.domain.Employer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DefaultEmployerServiceTest {

    @Mock
    private CollectionEmployerJPA collectionEmployerJPA;

    @InjectMocks
    private DefaultEmployerService defaultEmployerService;

    @Captor
    private ArgumentCaptor<Long> employerArgumentCaptor;

    @Test
    void save() {
        Employer employer = new Employer();
        employer.setName("arasaka");

        when(collectionEmployerJPA.save(employer)).thenReturn(employer);

        Employer savedEmployer = defaultEmployerService.save(employer);
        assertEquals(employer, savedEmployer);
        verify(collectionEmployerJPA).save(employer);
    }

    @Test
    void delete() {
        Employer employer = new Employer();
        employer.setName("arasaka");
        defaultEmployerService.delete(employer);
        verify(collectionEmployerJPA).delete(employer);
    }

    @Test
    void deleteAll() {
        defaultEmployerService.deleteAll();
        verify(collectionEmployerJPA).deleteAll();
    }

    @Test
    void saveAll() {
        Employer employer1 = new Employer();
        employer1.setName("arasaka");
        Employer employer2 = new Employer();
        employer2.setName("militech");

        List<Employer> employers = new ArrayList<>();
        employers.add(employer1);
        employers.add(employer2);

        defaultEmployerService.saveAll(employers);

        verify(collectionEmployerJPA).saveAll(employers);
    }

    @Test
    void findAll() {
        Employer employer1 = new Employer();
        employer1.setName("arasaka");
        Employer employer2 = new Employer();
        employer2.setName("militech");

        List<Employer> employers = new ArrayList<>();
        employers.add(employer1);
        employers.add(employer2);

        when(collectionEmployerJPA.findAll()).thenReturn(employers);

        List<Employer> currentEmployers = defaultEmployerService.findAll();
        assertEquals(employers, currentEmployers);
        assertNotNull(currentEmployers);
    }

    @Test
    void deleteById() {
        defaultEmployerService.deleteById(1L);
        verify(collectionEmployerJPA).deleteById(employerArgumentCaptor.capture());
        assertEquals(1, employerArgumentCaptor .getValue());
    }

    @Test
    void getOne() {
        Employer employer = new Employer();
        String lastName = "Tech Corp";
        employer.setName(lastName);
        employer.setId(1L);
        when(collectionEmployerJPA.getOne(1L)).thenReturn(employer);
        Employer CurrentEmployer = defaultEmployerService.getOne(1l);
        assertEquals(CurrentEmployer.getName(), CurrentEmployer.getName());
    }
}