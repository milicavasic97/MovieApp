package com.webapi.movieapp.service;

import com.webapi.movieapp.base.CrudJpaService;
import com.webapi.movieapp.models.ContentType;
import com.webapi.movieapp.repositories.ContentTypeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContentTypeService extends CrudJpaService<ContentType, Integer> {
    private final ContentTypeRepository contentTypeRepository;
    private final ModelMapper modelMapper;

    public ContentTypeService(ContentTypeRepository contentTypeRepository, ModelMapper modelMapper) {
        super(contentTypeRepository, modelMapper, ContentType.class);
        this.contentTypeRepository = contentTypeRepository;
        this.modelMapper = modelMapper;
    }

    public ContentType getContentTypeByName(String name){
        return contentTypeRepository.getByName(name);
    }

//    public ContentType save(ContentType request){
//        return contentTypeRepository.save(request);
//    }
//
//    public List<ContentType> getAll(){
//        return contentTypeRepository.findAll();
//    }
//
//    public ContentType getById(Integer id) { return contentTypeRepository.findById(id).get(); }
//
//    public Optional<ContentType> update(ContentType request)  {
//        return contentTypeRepository.findById(request.getContentTypeId()).map(item -> {
//            item.setName(request.getName());
//            contentTypeRepository.save(item);
//            return item;
//        });
//    }
//
//    public void delete(Integer id)  {
//        contentTypeRepository.deleteById(id);
//    }
}
