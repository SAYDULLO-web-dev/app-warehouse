package appwarehouse.service;

import appwarehouse.entity.Category;
import appwarehouse.model.CategoryAddDto;
import appwarehouse.model.CategoryDto;

import java.util.List;

public interface CategoryService {

    List<CategoryDto> getParents();

    List<CategoryDto> getChild(Long id);

    CategoryDto add(CategoryAddDto dto);

    Category validate(Long id);
}
