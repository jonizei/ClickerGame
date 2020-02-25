package com.github.jonizei.clickergame.applicationuser;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * This interface extends CrudRepository using values ApplicationUser as Entity and Integer as Id
 *
 * @author Joni Koskinen
 * @version 2020-02-25
 */
@Repository
public interface ApplicationUserRepository extends CrudRepository<ApplicationUser, Integer> {
    Optional<ApplicationUser> findByUsername(String username);
}
