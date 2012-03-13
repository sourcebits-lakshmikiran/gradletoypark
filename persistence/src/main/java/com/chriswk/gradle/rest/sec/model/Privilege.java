package com.chriswk.gradle.rest.sec.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

import com.chriswk.gradle.rest.common.INameableEntity;

@Entity
@XmlRootElement
public class Privilege implements INameableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PRIV_ID")
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;

    public Privilege() {
	super();
    }

    public Privilege(final String nameToSet) {
	super();
	name = nameToSet;
    }

    // API

    @Override
    public Long getId() {
	return id;
    }

    @Override
    public void setId(final Long idToSet) {
	id = idToSet;
    }

    @Override
    public String getName() {
	return name;
    }

    public void setName(final String nameToSet) {
	name = nameToSet;
    }

    //

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((name == null) ? 0 : name.hashCode());
	return result;
    }

    @Override
    public boolean equals(final Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	final Privilege other = (Privilege) obj;
	if (name == null) {
	    if (other.name != null)
		return false;
	} else if (!name.equals(other.name))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return getName();
    }

}
