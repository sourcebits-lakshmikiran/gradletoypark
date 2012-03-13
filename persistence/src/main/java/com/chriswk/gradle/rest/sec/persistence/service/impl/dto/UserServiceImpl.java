package com.chriswk.gradle.rest.sec.persistence.service.impl.dto;

import java.util.List;

import org.apache.commons.lang3.tuple.ImmutablePair;
import com.chriswk.gradle.rest.common.web.RestPreconditions;
import com.chriswk.gradle.rest.sec.dto.PrincipalToUserFunction;
import com.chriswk.gradle.rest.sec.dto.User;
import com.chriswk.gradle.rest.sec.model.Principal;
import com.chriswk.gradle.rest.sec.persistence.service.IPrincipalService;
import com.chriswk.gradle.rest.sec.persistence.service.dto.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    IPrincipalService principalService;

    public UserServiceImpl() {
super();
    }

    // API

    // search

    @Override
    public List<User> search(final ImmutablePair<String, ?>... constraints) {
throw new UnsupportedOperationException();
    }

    // find - one

    @Override
    public User findByName(final String name) {
final Principal principal = principalService.findByName(name);
return new User(principal);
    }

    @Override
    public User findOne(final long id) {
final Principal principal = principalService.findOne(id);
if (principal == null) {
return null;
}
return new User(principal);
    }

    // find - many

    @Override
    public List<User> findAll() {
final List<Principal> allPrincipalEntities = principalService.findAll();
final List<User> allUsers = Lists.transform(allPrincipalEntities, new PrincipalToUserFunction());

return allUsers;
    }

    @Override
    public Page<User> findPaginated(final int page, final int size, final String sortBy) {
final Page<Principal> principalsPaginated = principalService.findPaginated(page, size, sortBy);
final List<User> usersPaginated = Lists.transform(principalsPaginated.getContent(), new PrincipalToUserFunction());

Sort sortInfo = null;
if (sortBy != null) {
sortInfo = new Sort(sortBy);
}
return new PageImpl<User>(usersPaginated, new PageRequest(page, size, sortInfo), principalsPaginated.getTotalElements());
    }

    // create

    @Override
    public User create(final User entity) {
final Principal newPrincipalEntity = new Principal(entity.getName(), entity.getPassword(), entity.getRoles());
principalService.create(newPrincipalEntity);
entity.setId(newPrincipalEntity.getId());
return entity;
    }

    // update

    @Override
    public void update(final User entity) {
final Principal principalToUpdate = RestPreconditions.checkNotNull(principalService.findOne(entity.getId()));

principalToUpdate.setName(entity.getName());
principalToUpdate.setRoles(entity.getRoles());

principalService.update(principalToUpdate);
    }

    // delete

    @Override
    public void delete(final long id) {
principalService.delete(id);
    }

    @Override
    public void deleteAll() {
principalService.deleteAll();
    }

}