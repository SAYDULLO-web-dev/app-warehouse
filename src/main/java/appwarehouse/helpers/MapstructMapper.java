package appwarehouse.helpers;

import appwarehouse.entity.*;
import appwarehouse.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MapstructMapper {

    Warehouse toWarehouse(WarehouseAddDto dto);

    WarehouseDto toWarehouseDto(Warehouse warehouse);

    List<WarehouseDto> toWarehouseDto(List<Warehouse> warehouses);

    User toUser (UserAddDto userAddDto);

    UserDto toUserDto(User user);

    Category toCategory(CategoryAddDto dto);

    CategoryDto toCategoryDto(Category category);

    List<CategoryDto> toCategoryDto(List<Category> categories);

    Measurement toMeasurement(MeasurementAddDto dto);

    List<MeasurementDto> toMeasurementDto(List<Measurement> measurements);

    MeasurementDto toMeasurementDto(Measurement measurement);

    Product toProduct(ProductAddDto dto);

    ProductDto toProductDto(Product product);

    List<ProductDto> toProductDto(List<Product> products);

    AttachmentDto toAttachmentDto(Attachment attachment);
}
