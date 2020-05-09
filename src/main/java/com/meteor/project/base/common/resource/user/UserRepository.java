package com.meteor.project.base.common.resource.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.meteor.project.base.common.resource.security.PlatformUserRepository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User>, PlatformUserRepository {

	User findByUsername(String username);

}
