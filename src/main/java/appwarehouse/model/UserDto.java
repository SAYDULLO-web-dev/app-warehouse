package appwarehouse.model;

import appwarehouse.entity.Warehouse;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
public class UserDto implements Serializable {

    private Long id;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String code;

    private String password;

    public Boolean active;

    public Set<Warehouse> warehouses;
}
