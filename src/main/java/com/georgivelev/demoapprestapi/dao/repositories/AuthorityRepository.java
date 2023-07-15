package com.georgivelev.demoapprestapi.dao.repositories;

import com.georgivelev.demoapprestapi.entities.models.Authority;
import com.georgivelev.demoapprestapi.entities.models.UserAuthorities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface AuthorityRepository  extends JpaRepository<Authority, String> {
    Optional<Authority> findAuthorityByName(UserAuthorities name);

}
