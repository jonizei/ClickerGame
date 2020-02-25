package com.github.jonizei.clickergame.applicationuser;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApplicationUserRepository extends CrudRepository<ApplicationUser, Integer> {
    Optional<ApplicationUser> findByUsername(String username);
}
