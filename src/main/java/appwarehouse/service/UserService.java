package appwarehouse.service;

import appwarehouse.model.UserAddDto;
import appwarehouse.model.UserDto;

public interface UserService {

    UserDto add(UserAddDto dto);
}
