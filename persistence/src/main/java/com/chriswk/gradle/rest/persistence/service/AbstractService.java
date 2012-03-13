package com.chriswk.gradle.rest.persistence.service;

import java.util.List;

import org.apache.commons.lang3.tuple.ImmutablePair;
import com.chriswk.gradle.rest.common.IEntity;
import com.chriswk.gradle.rest.persistence.event.EntityCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;


@Transactional
public abstract class AbstractService<T extends IEntity> implements IService<T> {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    private Class<T> clazz;

    protected @Autowired
    ApplicationEventPublisher eventPublisher;

    public AbstractService(final Class<T> clazzToSet) {
		super();
		this.clazz = clazzToSet;
    }

    // API

    // search

    @Override
    public List<T> search(final ImmutablePair<String, ?>... constraints) {
		throw new UnsupportedOperationException();
    }

    // find/get

    @Override
    @Transactional(readOnly = true)
    public T findOne(final long id) {
		return this.getDao().findOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> findAll() {
		return Lists.newArrayList(this.getDao().findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Page<T> findPaginated(final int page, final int size, final String sortBy) {
		Sort sortInfo = null;
		if (sortBy != null) {
			sortInfo = new Sort(sortBy);
		}

		return getDao().findAll(new PageRequest(page, size, sortInfo));
    }

    // save/create/persist

    @Override
    public T create(final T entity) {
		Preconditions.checkNotNull(entity);

		final T persistedEntity = this.getDao().save(entity);

		eventPublisher.publishEvent(new EntityCreatedEvent<T>(this, clazz, persistedEntity));
		return persistedEntity;
    }

    // update/merge

    @Override
    public void update(final T entity) {
		Preconditions.checkNotNull(entity);
		// Preconditions.checkState( findOne( entity.getId() ) != null );

		this.getDao().save(entity);
    }

    // delete

    @Override
    public void deleteAll() {
		this.getDao().deleteAll();
    }

    @Override
    public void delete(final long id) {
		this.getDao().delete(id);
    }

    //

    protected abstract PagingAndSortingRepository<T, Long> getDao();

}
