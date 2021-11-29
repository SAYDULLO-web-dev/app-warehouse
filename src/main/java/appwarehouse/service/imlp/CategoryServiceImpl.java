package appwarehouse.service.imlp;

import appwarehouse.entity.Category;
import appwarehouse.helpers.MapstructMapper;
import appwarehouse.helpers.Utils;
import appwarehouse.model.CategoryAddDto;
import appwarehouse.model.CategoryDto;
import appwarehouse.repository.CategoryRepo;
import appwarehouse.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepo categoryRepo;
    private final MapstructMapper mapstructMapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepo categoryRepo, MapstructMapper mapstructMapper) {
        this.categoryRepo = categoryRepo;
        this.mapstructMapper = mapstructMapper;
    }

    @Override
    public List<CategoryDto> getParents() {
        List<Category> parents = categoryRepo.findAllParentCategories();
        return mapstructMapper.toCategoryDto(parents);
    }

    @Override
    public List<CategoryDto> getChild(Long id) {
        Category parentCategory = validate(id);
        List<Category> children=categoryRepo.findAllChildren(parentCategory);
        return mapstructMapper.toCategoryDto(children);
    }

    @Override
    public CategoryDto add(CategoryAddDto dto) {
        if (Utils.isEmpty(dto.getName())) {
            throw new RuntimeException("Category name shouldn't be empty!");
        } else {
            Optional<Category> categoryOptional = categoryRepo.findByName(dto.getName());
            if (categoryOptional.isPresent()) {
                throw new RuntimeException("This name is already exist.");
            }
        }

        Long parentCategoryId = dto.getParentCategoryId();
        Category parentCategory = null;

        if (!Utils.isEmpty(parentCategoryId)){
            Optional<Category> parentCategoryOptional = categoryRepo.findById(parentCategoryId);
            if (!parentCategoryOptional.isPresent()) {
                throw new RuntimeException("Parent category ID = " + parentCategoryId + " not found.");
            }
            parentCategory=parentCategoryOptional.get();
            if (!parentCategory.getActive()) {
                throw new RuntimeException("Parent category ID = " + parentCategoryId + " is inactive.");
            }
        }
        Category category= mapstructMapper.toCategory(dto);
        category.setParentCategory(parentCategory);

        Category savedCategory = categoryRepo.save(category);
        return mapstructMapper.toCategoryDto(savedCategory);
    }

    @Override
    public Category validate(Long id) {
        Optional<Category> categoryOpt = categoryRepo.findById(id);
        if (!categoryOpt.isPresent()){
            throw new RuntimeException("Category ID = " + id + " not found.");
        }
        Category category=categoryOpt.get();
        if (!category.getActive()) {
            throw new RuntimeException("Category ID = " + id + " is inactive.");
        }
        return category;
    }
}
