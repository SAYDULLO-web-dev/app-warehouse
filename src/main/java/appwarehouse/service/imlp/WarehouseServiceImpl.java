package appwarehouse.service.imlp;

import appwarehouse.entity.Warehouse;
import appwarehouse.helpers.MapstructMapper;
import appwarehouse.helpers.Utils;
import appwarehouse.model.WarehouseAddDto;
import appwarehouse.model.WarehouseDto;
import appwarehouse.repository.WarehouseRepo;
import appwarehouse.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class WarehouseServiceImpl implements WarehouseService {

    private final WarehouseRepo warehouseRepo;
    private final MapstructMapper mapstructMapper;

    @Autowired
    public WarehouseServiceImpl(WarehouseRepo warehouseRepo, MapstructMapper mapstructMapper) {
        this.warehouseRepo = warehouseRepo;
        this.mapstructMapper = mapstructMapper;
    }

    @Override
    public List<WarehouseDto> getList() {
        List<Warehouse> list = warehouseRepo.findAll();
//        List<WarehouseDto> warehouseDtos = mapstructMapper.toWarehouseDto(list);
//        return warehouseDtos;
        // YOKI
        return mapstructMapper.toWarehouseDto(list);
    }

    @Override
    public WarehouseDto add(WarehouseAddDto dto) {
        String name = dto.getName();
        if (Utils.isEmpty(name)){
            throw new RuntimeException("Warehouse's name is should not be null!");
        }else {
            Optional<Warehouse> warehouseOptional=warehouseRepo.findByName(name);
            if (warehouseOptional.isPresent()){
                throw new RuntimeException("This name is already existed!");
            }
        }
//        Warehouse warehouse=new Warehouse();
//        warehouse.setName(name);
//        warehouse.setActive(dto.getActive());

        Warehouse warehouse = mapstructMapper.toWarehouse(dto);

        Warehouse savedWarehouse = warehouseRepo.save(warehouse);

//        WarehouseDto warehouseDto = new WarehouseDto();
//        warehouseDto.setId(savedWarehouse.getId());
//        warehouseDto.setName(savedWarehouse.getName());
//        warehouseDto.setActive(savedWarehouse.getActive());

        WarehouseDto warehouseDto = mapstructMapper.toWarehouseDto(savedWarehouse);

        return warehouseDto;
    }

    @Override
    public boolean active(Warehouse warehouse) {
        if (Objects.isNull(warehouse)){
            return false;
        }
        return warehouse.getActive();
    }
}
