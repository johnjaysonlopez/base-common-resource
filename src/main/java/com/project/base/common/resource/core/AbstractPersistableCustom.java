package com.project.base.common.resource.core;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.springframework.data.domain.Persistable;

@MappedSuperclass
public class AbstractPersistableCustom<PK extends Serializable> implements Persistable<Long> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Override
	public Long getId() {
		return this.id;
	}

	protected void setId(final Long id) {
		this.id = id;
	}

	@Override
	public boolean isNew() {
		return null == this.id;
	}

}
