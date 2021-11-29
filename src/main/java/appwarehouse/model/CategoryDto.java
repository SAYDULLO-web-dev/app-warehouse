package appwarehouse.model;

import appwarehouse.entity.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDto extends AbsDto {

    private Category parentCategory;
}
