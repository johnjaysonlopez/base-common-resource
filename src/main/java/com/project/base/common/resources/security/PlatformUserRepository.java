package com.project.base.common.resources.security;

public interface PlatformUserRepository {

	PlatformUser findByUsernameAndDeletedAndEnabled(String username, boolean deleted, boolean enabled);

}
