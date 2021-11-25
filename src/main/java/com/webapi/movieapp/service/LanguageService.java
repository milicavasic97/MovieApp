package com.webapi.movieapp.service;

import com.webapi.movieapp.base.CrudJpaService;
import com.webapi.movieapp.models.Language;
import com.webapi.movieapp.repositories.LanguageRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LanguageService extends CrudJpaService<Language, Integer> {
    private final LanguageRepository languageRepository;
    private final ModelMapper modelMapper;

    public LanguageService(LanguageRepository languageRepository, ModelMapper modelMapper) {
        super(languageRepository, modelMapper, Language.class);
        this.languageRepository = languageRepository;
        this.modelMapper = modelMapper;
    }

//
//    public Language save(Language request){
//        return languageRepository.save(request);
//    }
//
//    public List<Language> getAll(){
//        return languageRepository.findAll();
//    }
//
//    public Language getById(Integer id) { return languageRepository.findById(id).get(); }
//
//    public Optional<Language> update(Language request)  {
//        return languageRepository.findById(request.getLanguageId()).map(item -> {
//            item.setName(request.getName());
//            item.setCode(request.getCode());
//            languageRepository.save(item);
//            return item;
//        });
//    }
//
//    public void delete(Integer id)  {
//        languageRepository.deleteById(id);
//    }
}
