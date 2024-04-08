package it.contrader.dao;

import it.contrader.model.Registry;
import it.contrader.model.Test;
import it.contrader.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RegistryRepository extends JpaRepository<Registry, Long> {

    Optional<Registry>  findByUserFkEmail(String email);

    Optional<Registry>  findByUserFkId(Long id);
    Optional<List<Registry>>  findByUserFkUsertype(User.Usertype usertype);

}
