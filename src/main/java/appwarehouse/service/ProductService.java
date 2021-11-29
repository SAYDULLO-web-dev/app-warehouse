package appwarehouse.service;

import appwarehouse.model.ProductAddDto;
import appwarehouse.model.ProductDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {

    ProductDto get(Long id);

    List<ProductDto> getAll();

    List<ProductDto> getAllByCategory(Long categoryId);

    List<ProductDto> getAllByMeasurement(Long measurementId);

    ProductDto add(ProductAddDto dto, MultipartFile file) throws IOException;
}
