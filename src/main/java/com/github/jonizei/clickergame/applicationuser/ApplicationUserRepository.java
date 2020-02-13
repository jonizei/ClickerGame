package com.github.jonizei.clickergame.applicationuser;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ApplicationUserRepository extends CrudRepository<ApplicationUser, Integer> {
    Optional<ApplicationUser> findByUsername(String username);
}
