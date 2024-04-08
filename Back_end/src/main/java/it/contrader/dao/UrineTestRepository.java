package it.contrader.dao;

import it.contrader.model.BloodTest;
import it.contrader.model.UrineTest;
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
public interface UrineTestRepository extends JpaRepository<UrineTest, Long> {
    Optional<UrineTest>  findByTestFkId(Long id);
}
