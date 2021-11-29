package appwarehouse.model;

import appwarehouse.entity.Attachment;
import appwarehouse.entity.Category;
import appwarehouse.entity.Measurement;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto extends AbsDto {

    private String code;

    private Category category;

    private Attachment attachment;

    private Measurement measurement;
}
