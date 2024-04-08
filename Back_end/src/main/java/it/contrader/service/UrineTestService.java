package it.contrader.service;

import it.contrader.dao.BloodTestRepository;
import it.contrader.dao.UrineTestRepository;
import it.contrader.dto.BloodTestDTO;
import it.contrader.dto.UrineTestDTO;
import it.contrader.model.UrineTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UrineTestService extends AbstractService<UrineTest, UrineTestDTO> {
    @Autowired
    private UrineTestRepository repository;
    public UrineTestDTO findByIdTestFk(Long id){
        return converter.toDTO(repository.findByTestFkId(id).orElseThrow(() -> new RuntimeException("Referto non trovato!")));
    }
}
