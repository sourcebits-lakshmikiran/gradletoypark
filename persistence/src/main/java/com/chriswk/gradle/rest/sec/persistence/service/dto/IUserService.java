package com.chriswk.gradle.rest.sec.persistence.service.dto;

import com.chriswk.gradle.rest.persistence.service.IService;
import com.chriswk.gradle.rest.sec.dto.User;

public interface IUserService extends IService<User> {

    User findByName(final String name);

}