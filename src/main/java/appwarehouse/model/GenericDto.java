package appwarehouse.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
@Getter
@Setter
public abstract class GenericDto implements Serializable {

    private String name;

    private boolean active;
}
