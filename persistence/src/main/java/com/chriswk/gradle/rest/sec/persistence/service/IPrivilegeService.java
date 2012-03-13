package com.chriswk.gradle.rest.sec.persistence.service;

import com.chriswk.gradle.rest.persistence.service.IService;
import com.chriswk.gradle.rest.sec.model.Privilege;

public interface IPrivilegeService extends IService<Privilege> {

    Privilege findByName(final String name);

}