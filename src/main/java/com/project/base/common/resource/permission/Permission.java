package com.project.base.common.resource.permission;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.project.base.common.resource.core.AbstractPersistableCustom;

import lombok.Getter;

@Getter
@Entity
@Table(name = "permission")
public class Permission extends AbstractPersistableCustom<Long> {

	private static final long serialVersionUID = 1L;

	@Column(name = "grouping", nullable = false, length = 45)
	private String grouping;

	@Column(name = "code", nullable = false, length = 100)
	private String code;

	@Column(name = "entity_name", nullable = true, length = 100)
	private String entityName;

	@Column(name = "action_name", nullable = true, length = 100)
	private String actionName;

	@Column(name = "can_maker_checker", nullable = false)
	private boolean canMakerChecker;

}
