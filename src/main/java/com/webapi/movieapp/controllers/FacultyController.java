package com.webapi.movieapp.controllers;

import com.webapi.movieapp.models.Faculty;
import com.webapi.movieapp.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/faculties")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("/test")
    public String test(@RequestParam String id){
        return "hsbkhsidl " + id;
    }

    @PostMapping("/save")
    public Faculty saveFaculty(@RequestBody Faculty requestBody){
        return facultyService.saveFaculty(requestBody);
    }

    @GetMapping
    public List<Faculty> getAll(){
        return facultyService.getAll();
    }

    @PutMapping("/update")
    public Optional<Faculty> updateFaculty(@RequestBody Faculty request)  {
        return facultyService.updateFaculty(request);
    }

    @DeleteMapping("/delete")
    public void deleteFaculty(@RequestParam String facultyId) {
        facultyService.deleteFaculty(facultyId);
    }


}
