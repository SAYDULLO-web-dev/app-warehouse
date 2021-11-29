package appwarehouse.controller;

import appwarehouse.model.ProductAddDto;
import appwarehouse.model.ProductDto;
import appwarehouse.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController{

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/get/{id}")
    private ProductDto get(@PathVariable(value = "id") Long id){
        return productService.get(id);
    }

    @GetMapping("/get/all")
    private List<ProductDto> getAll(){
        return productService.getAll();
    }

    @GetMapping("/get/category/{id}/products")
    private List<ProductDto> getAllByCategory(@PathVariable(value = "id") Long categoryId){
        return productService.getAllByCategory(categoryId);
    }

    @GetMapping("/get/measurement/{id}/products")
    private List<ProductDto> getAllByMeasurement(@PathVariable(value = "id") Long measurementId){
        return productService.getAllByMeasurement(measurementId);
    }

    @PostMapping("/add")
    private ProductDto add(@RequestBody ProductAddDto dto, @RequestParam(value = "/file") MultipartFile file) throws IOException {
        return productService.add(dto, file);
    }
}
