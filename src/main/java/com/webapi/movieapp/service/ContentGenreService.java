package com.webapi.movieapp.service;

import com.webapi.movieapp.base.CrudJpaService;
import com.webapi.movieapp.models.ContentGenre;
import com.webapi.movieapp.models.ContentGenreId;
import com.webapi.movieapp.repositories.ContentGenreRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentGenreService extends CrudJpaService<ContentGenre, ContentGenreId> {
    private final ContentGenreRepository contentGenreRepository;
    private final ModelMapper modelMapper;

    public ContentGenreService(ContentGenreRepository contentGenreRepository, ModelMapper modelMapper) {
        super(contentGenreRepository, modelMapper, ContentGenre.class);
        this.contentGenreRepository = contentGenreRepository;
        this.modelMapper = modelMapper;
    }

    public void deleteByContentId(Integer contentId){
        contentGenreRepository.deleteAllByContent_ContentId(contentId);
    }

    public List<ContentGenre> getAllByContentId(Integer contentId){
        return contentGenreRepository.findAllByContent_ContentId(contentId);
    }

//    public ContentGenre save(ContentGenre request){
//        return contentGenreRepository.save(request);
//    }
//
//    public List<ContentGenre> getAll(){
//        return contentGenreRepository.findAll();
//    }
//
//    public ContentGenre getById(ContentGenreId id) { return contentGenreRepository.findById(id).get(); }
//
//    public void delete(ContentGenreId id)  {
//        contentGenreRepository.deleteById(id);
//    }
}
