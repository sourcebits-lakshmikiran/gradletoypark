package com.chriswk.gradle.rest.sec.persistence.service;

import com.chriswk.gradle.rest.persistence.service.IService;
import com.chriswk.gradle.rest.sec.model.Role;

public interface IRoleService extends IService<Role> {

    Role findByName(final String name);

}