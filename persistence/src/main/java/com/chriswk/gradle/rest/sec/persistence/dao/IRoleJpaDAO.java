package com.chriswk.gradle.rest.sec.persistence.dao;

import com.chriswk.gradle.rest.sec.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IRoleJpaDAO extends JpaRepository<Role, Long>, JpaSpecificationExecutor<Role> {

    Role findByName(final String name);

}