package appwarehouse.service.imlp;

import appwarehouse.entity.User;
import appwarehouse.entity.Warehouse;
import appwarehouse.helpers.MapstructMapper;
import appwarehouse.helpers.Utils;
import appwarehouse.model.UserAddDto;
import appwarehouse.model.UserDto;
import appwarehouse.repository.UserRepo;
import appwarehouse.repository.WarehouseRepo;
import appwarehouse.service.UserService;
import appwarehouse.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final WarehouseRepo warehouseRepo;
    private final MapstructMapper mapstructMapper;
    private final WarehouseService warehouseService;

    @Autowired
    public UserServiceImpl(UserRepo userRepo, WarehouseRepo warehouseRepo, MapstructMapper mapstructMapper, WarehouseService warehouseService) {
        this.userRepo = userRepo;
        this.warehouseRepo = warehouseRepo;
        this.mapstructMapper = mapstructMapper;
        this.warehouseService = warehouseService;
    }

    @Override
    public UserDto add(UserAddDto dto) {
        String phoneNumber= dto.getPhoneNumber();
        if (Utils.isEmpty(dto.getPassword())){
            throw new RuntimeException("User phone number is should not be empty!");
        }else {
            Optional<User> userOptional=userRepo.findByPhoneNumber(phoneNumber);
            if (userOptional.isPresent()){
                throw new RuntimeException("This phone number is already exist!");
            }
        }
        Set<Warehouse> warehouseSet=new HashSet<>();

        for (Long warehouseId : dto.getWarehouseIds()) {
            if (Utils.isEmpty(dto.getWarehouseIds())){
                continue;
            }
            Optional<Warehouse> warehouseOptional=warehouseRepo.findById(warehouseId);
            if (warehouseOptional.isPresent()){
                if (warehouseService.active(warehouseOptional.get())){
                    warehouseSet.add(warehouseOptional.get());
                }
            }
        }

        User user= mapstructMapper.toUser(dto);
        user.setCode(Utils.generateCode());
        user.setWarehouses(warehouseSet);

        User savedUser = userRepo.save(user);

        UserDto userDto= mapstructMapper.toUserDto(savedUser);
        return userDto;
    }
}
