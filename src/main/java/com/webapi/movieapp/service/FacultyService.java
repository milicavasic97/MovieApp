package com.webapi.movieapp.service;

import com.webapi.movieapp.models.Faculty;
import com.webapi.movieapp.repositories.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty saveFaculty( Faculty requestBody){
        requestBody.setFacultyId();
        return facultyRepository.save(requestBody);
    }

    public List<Faculty> getAll(){
        return facultyRepository.findAll();
    }

    public Optional<Faculty> updateFaculty(Faculty request)  {
        return facultyRepository.findById(request.getFacultyId()).map(item -> {
            item.setName(request.getName());
            facultyRepository.save(item);
            return item;
        });
    }

    public void deleteFaculty(String facultyId)  {
        facultyRepository.deleteById(facultyId);
    }
}
