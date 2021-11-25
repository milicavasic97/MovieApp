package com.webapi.movieapp.controllers;

import com.webapi.movieapp.dtos.LanguageDTO;
import com.webapi.movieapp.models.Language;
import com.webapi.movieapp.service.LanguageService;
import javassist.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/languages")
public class LanguageController {
    private final LanguageService languageService;

    public LanguageController(LanguageService languageService) {
        this.languageService = languageService;
    }

    @GetMapping("/get-one")
    public LanguageDTO getOne(@RequestParam Integer id) throws NotFoundException {
        return languageService.findById(id, LanguageDTO.class);
    }

    @GetMapping
    public List<LanguageDTO> getAll(){
        return languageService.findAll(LanguageDTO.class);
    }

    @PostMapping("/save")
    public LanguageDTO insert(@RequestBody LanguageDTO request) {
        return languageService.insert(request, LanguageDTO.class);
    }

    @PutMapping("/update")
    public LanguageDTO update(@RequestBody LanguageDTO request) throws NotFoundException {
        return languageService.update(request.getLanguageId(), request, LanguageDTO.class);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam Integer id) throws NotFoundException {
        languageService.delete(id);
    }
}
