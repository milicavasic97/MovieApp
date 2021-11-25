package com.webapi.movieapp.controllers;

import com.webapi.movieapp.models.ContentType;
import com.webapi.movieapp.service.ContentTypeService;
import javassist.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/content-types")
public class ContentTypeController {
    private final ContentTypeService contentTypeService;

    public ContentTypeController(ContentTypeService contentTypeService) {
        this.contentTypeService = contentTypeService;
    }

    @GetMapping
    public List<ContentType> getAll(){
        return contentTypeService.findAll(ContentType.class);
    }

    @PostMapping("/save")
    public ContentType insert(@RequestBody ContentType request) {
        return contentTypeService.insert(request, ContentType.class);
    }

    @PutMapping("/update")
    public ContentType update(@RequestBody ContentType request) throws NotFoundException {
        return contentTypeService.update(request.getContentTypeId(),request, ContentType.class);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam Integer id) throws NotFoundException {
        contentTypeService.delete(id);
    }
}
