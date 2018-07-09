package com.dengjk.clouduserprovid.servier.impl;

import com.dengjk.clouduserprovid.dao.UserRepository;
import com.dengjk.clouduserprovid.entity.UserEntity;
import com.dengjk.clouduserprovid.servier.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserEntity getUserById(Integer id ){
        Pageable page =new PageRequest(0,10, Sort.Direction.ASC,"id");
        return userRepository.findOne(id);
    }
}
