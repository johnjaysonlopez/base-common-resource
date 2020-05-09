package com.project.base.common.resource.role;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.project.base.common.resource.core.AbstractPersistableCustom;
import com.project.base.common.resource.permission.Permission;

import lombok.Getter;

@Getter
@Entity
@Table(name = "role", uniqueConstraints = { @UniqueConstraint(columnNames = { "name" }, name = "unq_name") })
public class Role extends AbstractPersistableCustom<Long> {

	private static final long serialVersionUID = 1L;

	@Column(name = "name", unique = true, nullable = false, length = 100)
	private String name;

	@Column(name = "description", nullable = false, length = 500)
	private String description;

	@Column(name = "is_disabled", nullable = false)
	private Boolean disabled;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "role_permission", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "permission_id"))
	private Set<Permission> permissions = new HashSet<>();

}
