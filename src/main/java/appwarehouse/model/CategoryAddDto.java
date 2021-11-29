package appwarehouse.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryAddDto extends GenericDto{

    private Long parentCategoryId;
}
