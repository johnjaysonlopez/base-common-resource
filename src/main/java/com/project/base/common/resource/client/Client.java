package com.project.base.common.resource.client;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.base.common.resource.core.AbstractPersistableCustom;

@Entity
@Table(name = "client", uniqueConstraints = { @UniqueConstraint(columnNames = {"client_id"}) })
public class Client extends AbstractPersistableCustom<Serializable> implements ClientDetails {

	private static final long serialVersionUID = 1L;

	@Column(name = "client_id", nullable = false, unique = true, length = 100)
	private String clientId;

	@Column(name = "client_secret", nullable = false, unique = true, length = 100)
	private String clientSecret;

	@Column(name = "resource_ids", length = 500)
	private String resourceIds;

	@Column(name = "scope", length = 100)
	private String scope;

	@Column(name = "authorized_grant_types", nullable = false, length = 200)
	private String authorizedGrantTypes;

	@Column(name = "web_server_redirect_uri", length = 500)
	private String registeredRedirectUri;

	@Column(name = "authorities", length = 200)
	private String authorities;

	@Column(name = "access_token_validity", nullable = false)
	private Integer accessTokenValiditySeconds;

	@Column(name = "refresh_token_validity", nullable = false)
	private Integer refreshTokenValiditySeconds;

	@Column(name = "autoapprove", length = 100)
	private String autoApproveScope;

	@Column(name = "additional_information", length = 500)
	private String additionalInformation;


	private Set<String> getAutoApproveScope() {
		return StringUtils.commaDelimitedListToSet(this.autoApproveScope);
	}

	@Override
	public String getClientId() {
		return this.clientId;
	}

	@Override
	public Set<String> getResourceIds() {
		if (StringUtils.isEmpty(this.resourceIds)) {
			return new HashSet<>();
		} else {
			return StringUtils.commaDelimitedListToSet(this.resourceIds);
		}
	}

	@Override
	public boolean isSecretRequired() {
		return !StringUtils.isEmpty(this.clientSecret);
	}

	@Override
	public String getClientSecret() {
		return this.clientSecret;
	}

	@Override
	public boolean isScoped() {
		return this.getScope().size() > 0;
	}

	@Override
	public Set<String> getScope() {
		return StringUtils.commaDelimitedListToSet(this.scope);
	}

	@Override
	public Set<String> getAuthorizedGrantTypes() {
		return StringUtils.commaDelimitedListToSet(this.authorizedGrantTypes);
	}

	@Override
	public Set<String> getRegisteredRedirectUri() {
		return StringUtils.commaDelimitedListToSet(this.registeredRedirectUri);
	}

	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		Set<String> set = StringUtils.commaDelimitedListToSet(this.authorities);
		Set<GrantedAuthority> result = new HashSet<>();
		set.forEach(authority -> result.add(new GrantedAuthority() {

			private static final long serialVersionUID = 1L;

			@Override
			public String getAuthority() {
				return authority;
			}
		}));
		return result;
	}

	@Override
	public Integer getAccessTokenValiditySeconds() {
		return this.accessTokenValiditySeconds;
	}

	@Override
	public Integer getRefreshTokenValiditySeconds() {
		return this.refreshTokenValiditySeconds;
	}

	@Override
	public boolean isAutoApprove(String scope) {
		if (this.autoApproveScope == null) {
			return false;
		} else {
			Iterator<String> scopeIterator = this.getAutoApproveScope().iterator();
			String auto;
			do {
				if (!scopeIterator.hasNext()) {
					return false;
				}
				auto = (String) scopeIterator.next();
			} while (!auto.equals("true") && !scope.matches(auto));
			return true;
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public Map<String, Object> getAdditionalInformation() {
		try {
			return new ObjectMapper().readValue(this.additionalInformation, Map.class);
		} catch (IOException e) {
			return new HashMap<>();
		}
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public void setResourceIds(Set<String> resourceIds) {
		this.resourceIds = StringUtils.collectionToCommaDelimitedString(resourceIds);
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public void setScope(Set<String> scope) {
		this.scope = StringUtils.collectionToCommaDelimitedString(scope);
	}

	public void setAuthorizedGrantTypes(Set<String> authorizedGrantType) {
		this.authorizedGrantTypes = StringUtils.collectionToCommaDelimitedString(authorizedGrantType);
	}

	public void setRegisteredRedirectUri(Set<String> registeredRedirectUriList) {
		this.registeredRedirectUri = StringUtils.collectionToCommaDelimitedString(registeredRedirectUriList);
	}

	public void setAuthorities(Collection<GrantedAuthority> authorities) {
		this.authorities = StringUtils.collectionToCommaDelimitedString(authorities);
	}

	public void setAccessTokenValiditySeconds(Integer accessTokenValiditySeconds) {
		this.accessTokenValiditySeconds = accessTokenValiditySeconds;
	}

	public void setRefreshTokenValiditySeconds(Integer refreshTokenValiditySeconds) {
		this.refreshTokenValiditySeconds = refreshTokenValiditySeconds;
	}

	public void setAutoApproveScope(Set<String> autoApproveScope) {
		this.autoApproveScope = StringUtils.collectionToCommaDelimitedString(autoApproveScope);
	}

	public void setAdditionalInformation(Map<String, Object> additionalInformation) {
		try {
			this.additionalInformation = new ObjectMapper().writeValueAsString(additionalInformation);
		} catch (IOException e) {
			this.additionalInformation = "";
		}
	}

}

