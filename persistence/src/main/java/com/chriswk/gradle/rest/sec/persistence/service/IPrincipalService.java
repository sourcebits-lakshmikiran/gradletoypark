package com.chriswk.gradle.rest.sec.persistence.service;

import com.chriswk.gradle.rest.persistence.service.IService;
import com.chriswk.gradle.rest.sec.model.Principal;

public interface IPrincipalService extends IService<Principal> {

    Principal findByName(final String name);

}