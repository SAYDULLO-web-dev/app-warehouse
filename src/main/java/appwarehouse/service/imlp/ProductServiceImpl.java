package appwarehouse.service.imlp;

import appwarehouse.entity.Attachment;
import appwarehouse.entity.Category;
import appwarehouse.entity.Measurement;
import appwarehouse.entity.Product;
import appwarehouse.helpers.MapstructMapper;
import appwarehouse.helpers.Utils;
import appwarehouse.model.ProductAddDto;
import appwarehouse.model.ProductDto;
import appwarehouse.repository.ProductRepo;
import appwarehouse.service.AttachmentService;
import appwarehouse.service.CategoryService;
import appwarehouse.service.MeasurementService;
import appwarehouse.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepo;
    private final MapstructMapper mapstructMapper;
    private final CategoryService categoryService;
    private final MeasurementService measurementService;
    private final AttachmentService attachmentService;

    @Autowired
    public ProductServiceImpl(ProductRepo productRepo, MapstructMapper mapstructMapper, CategoryServiceImpl categoryService, MeasurementServiceImpl measurementService, AttachmentServiceImpl attachmentService) {
        this.productRepo = productRepo;
        this.mapstructMapper = mapstructMapper;
        this.categoryService = categoryService;
        this.measurementService = measurementService;
        this.attachmentService = attachmentService;
    }

    @Override
    public ProductDto get(Long id) {
        return null;
    }

    @Override
    public List<ProductDto> getAll() {
        List<Product> list = productRepo.findAll();
        return mapstructMapper.toProductDto(list);
    }

    @Override
    public List<ProductDto> getAllByCategory(Long categoryId) {
        List<Product> list= productRepo.findAllByCategoryId(categoryId);
        return mapstructMapper.toProductDto(list);
    }

    @Override
    public List<ProductDto> getAllByMeasurement(Long measurementId) {
        List<Product> list= productRepo.findAllByMeasurementId(measurementId);
        return mapstructMapper.toProductDto(list);
    }

    @Override
    public ProductDto add(ProductAddDto dto, MultipartFile file) throws IOException {

        // Checking ProductAddDto.name
        String name = dto.getName();
        if (Utils.isEmpty(name)){
            throw new RuntimeException("Product's name is should not be null!");
        }else {
            Optional<Product> productOptional=productRepo.findByName(name);
            if (productOptional.isPresent()){
                throw new RuntimeException("This name is already existed!");
            }
        }

        // Checking ProductAddDto.categoryId
        Category category= categoryService.validate(dto.getCategoryId());

        // Checking ProductAddDto.measurementId
        Measurement measurement= measurementService.validate(dto.getMeasurementId());

        // Setting information from ProductAddDto to Product after checking
        Product product= mapstructMapper.toProduct(dto);
        product.setName(name);
        product.setCategory(category);
        product.setMeasurement(measurement);
        product.setCode(Utils.generateCode());

        Attachment attachment = attachmentService.upload(file);  // Uploading file
        product.setAttachment(attachment);

        // saving product to database
        Product savedProduct = productRepo.save(product);

        // returning response object
        return mapstructMapper.toProductDto(savedProduct);
    }
}
