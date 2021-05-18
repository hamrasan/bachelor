package cz.fel.cvut.hamrasan.gardener.service;

import cz.fel.cvut.hamrasan.gardener.dao.PlantCategoryDao;
import cz.fel.cvut.hamrasan.gardener.dto.CategoryDto;
import cz.fel.cvut.hamrasan.gardener.model.PlantCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public
class PlantCategoryService {

    private PlantCategoryDao plantCategoryDao;
    private TranslateService translateService;


    @Autowired
    public PlantCategoryService(PlantCategoryDao plantCategoryDao, TranslateService translateService) {
        this.plantCategoryDao = plantCategoryDao;
        this.translateService = translateService;
    }

    @Transactional
    public List<CategoryDto> findAll() {
        List<CategoryDto> dtos = new ArrayList<CategoryDto>();

        for (PlantCategory category: plantCategoryDao.findAll() ) {
            dtos.add(translateService.translateCategory(category));
        }

        return dtos;
    }


    @Transactional
    public CategoryDto find(Long id) {
        return translateService.translateCategory(plantCategoryDao.find(id));
    }

}
