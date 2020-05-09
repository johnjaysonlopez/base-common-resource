package com.meteor.project.base.common.resource.security;

public interface PlatformUserRepository {

	PlatformUser findByUsernameAndDeletedAndEnabled(String username, boolean deleted, boolean enabled);

}
