package appwarehouse.controller;

import appwarehouse.model.CategoryAddDto;
import appwarehouse.model.CategoryDto;
import appwarehouse.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/parents")
    private List<CategoryDto> getParents(){
        return categoryService.getParents();
    }

    @GetMapping("/{id}/child")
    private List<CategoryDto> getChild(@PathVariable(value = "id") Long id){
        return categoryService.getChild(id);
    }

    @PostMapping("/add")
    private CategoryDto add(@RequestBody CategoryAddDto dto){
        return categoryService.add(dto);
    }
}
