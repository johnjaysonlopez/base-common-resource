package com.project.base.common.resources.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.project.base.common.resources.core.AbstractPersistableCustom;
import com.project.base.common.resources.permission.Permission;
import com.project.base.common.resources.role.Role;
import com.project.base.common.resources.security.PlatformUser;

@Entity
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = { "username" }, name = "username_org"))
public class User extends AbstractPersistableCustom<Serializable> implements PlatformUser {

	private static final long serialVersionUID = 1L;

	@Column(name = "email", nullable = false, length = 100)
	private String email;

	@Column(name = "username", nullable = false, length = 100)
	private String username;

	@Column(name = "firstname", nullable = false, length = 100)
	private String firstname;

	@Column(name = "lastname", nullable = false, length = 100)
	private String lastname;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "nonexpired", nullable = false)
	private boolean accountNonExpired;

	@Column(name = "nonlocked", nullable = false)
	private boolean accountNonLocked;

	@Column(name = "nonexpired_credentials", nullable = false)
	private boolean credentialsNonExpired;

	@Column(name = "enabled", nullable = false)
	private boolean enabled;

	@Column(name = "is_deleted", nullable = false)
	private boolean deleted;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;

	@Column(name = "last_time_password_updated")
	@Temporal(TemporalType.DATE)
	private Date lastTimePasswordUpdated;

	@Column(name = "password_never_expires", nullable = false)
	private boolean passwordNeverExpires;


	public boolean isDeleted() {
		return this.deleted;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		final List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		for (final Role role : this.roles) {
			final Collection<Permission> permissions = role.getPermissions();
			for (final Permission permission : permissions) {
				grantedAuthorities.add(new SimpleGrantedAuthority(permission.getCode()));
			}
		}
		return grantedAuthorities;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return this.accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.credentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return this.enabled;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public String getLastname() {
		return this.lastname;
	}

	public String getEmail() {
		return this.email;
	}

	public Set<Role> getRoles() {
		return this.roles;
	}

	public boolean getPasswordNeverExpires() {
		return this.passwordNeverExpires;
	}

	public Date getLastTimePasswordUpdated() {
		return this.lastTimePasswordUpdated;
	}

	public boolean isNotEnabled() {
		return !isEnabled();
	}

}
