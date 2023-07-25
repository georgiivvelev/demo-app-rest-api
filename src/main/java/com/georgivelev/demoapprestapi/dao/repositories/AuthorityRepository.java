package com.georgivelev.demoapprestapi.dao.repositories;

import com.georgivelev.demoapprestapi.aop.LogCustomAnnotation;
import com.georgivelev.demoapprestapi.entities.models.Authority;
import com.georgivelev.demoapprestapi.entities.models.UserAuthorities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorityRepository  extends JpaRepository<Authority, String> {

    @LogCustomAnnotation
    Optional<Authority> findAuthorityByName(UserAuthorities name);

}
