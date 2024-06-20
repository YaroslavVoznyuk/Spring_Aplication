package com.danIT.HW.Spring_Aplication.HW5.services;

import com.danIT.HW.Spring_Aplication.HW5.dao.CollectionUserJPA;
import com.danIT.HW.Spring_Aplication.HW5.domain.User;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final CollectionUserJPA collectionUserJPA;

    public Optional<User> getByLogin(@NonNull String login) {
        return collectionUserJPA.findUsersByUserName(login);
    }

}