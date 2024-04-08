package it.contrader.dao;

import it.contrader.model.BloodTest;
import it.contrader.model.Registry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;


/**
 * Estende CrudRepository ed eredita tutti i metodi di CRUD. 
 * Definisce il metodo di login.
 * 
 * @author Vittorio Valent & Girolamo Murdaca
 *
 * @see CrudRepository
 *
 */
@Repository
@Transactional
public interface BloodTestRepository extends JpaRepository<BloodTest, Long> {

    Optional<BloodTest>  findByTestFkId(Long id);
}
