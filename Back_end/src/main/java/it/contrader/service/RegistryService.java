package it.contrader.service;

import it.contrader.converter.RegistryConverter;
import it.contrader.dao.RegistryRepository;
import it.contrader.dto.RegistryDTO;
import it.contrader.model.Registry;
import it.contrader.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistryService extends AbstractService<Registry, RegistryDTO>{

    @Autowired
    private RegistryRepository repository;

    @Autowired
    private RegistryConverter converter;

    public RegistryDTO findByUserFkEmail(String email){
        return converter.toDTO(repository.findByUserFkEmail(email).orElseThrow(() -> new RuntimeException("Email non trovata!")));
    }

    public RegistryDTO findByUserFk(Long id){
        return converter.toDTO(repository.findByUserFkId(id).orElseThrow(() -> new RuntimeException("Utente non trovato!")));
    }


    public List<RegistryDTO> findByUsertype(User.Usertype usertype){
        return converter.toDTOList(repository.findByUserFkUsertype(usertype).orElseThrow(() -> new RuntimeException("Utenti non trovati!")));
    }
}
