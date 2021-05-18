package cz.fel.cvut.hamrasan.gardener.rest;

import cz.fel.cvut.hamrasan.gardener.dto.CategoryDto;
import cz.fel.cvut.hamrasan.gardener.exceptions.NotFoundException;
import cz.fel.cvut.hamrasan.gardener.security.SecurityConstants;
import cz.fel.cvut.hamrasan.gardener.service.PlantCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@CrossOrigin(origins = SecurityConstants.ORIGIN_URI, allowCredentials="true")
public class CategoryController {

    private PlantCategoryService categoryService;

    @Autowired
    public CategoryController(PlantCategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping( produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CategoryDto> getAll(){
        return categoryService.findAll();
    }

    @GetMapping( value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CategoryDto getPlantCategory(@PathVariable Long id ) throws NotFoundException {
        return categoryService.find(id);
    }
}
