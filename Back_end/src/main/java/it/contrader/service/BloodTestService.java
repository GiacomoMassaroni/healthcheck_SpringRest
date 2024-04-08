package it.contrader.service;


import it.contrader.dao.RegistryRepository;
import it.contrader.dto.BloodTestDTO;
import it.contrader.dao.BloodTestRepository;
import it.contrader.model.BloodTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BloodTestService extends AbstractService<BloodTest,BloodTestDTO> {

    @Autowired
    private BloodTestRepository repository;
    public BloodTestDTO findByIdTestFk(Long id){
        return converter.toDTO(repository.findByTestFkId(id).orElseThrow(() -> new RuntimeException("Referto non trovato!")));
    }

}
