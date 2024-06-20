package com.danIT.HW.Spring_Aplication.HW5.dao;

import com.danIT.HW.Spring_Aplication.HW5.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CollectionUserJPA extends CrudRepository<User, Long> {
    Optional<User> findUsersByUserName(String userName);

}
