package appwarehouse.service;


import appwarehouse.entity.Warehouse;
import appwarehouse.model.WarehouseAddDto;
import appwarehouse.model.WarehouseDto;

import java.util.List;

public interface WarehouseService {

    List<WarehouseDto> getList();

    WarehouseDto add(WarehouseAddDto dto);

    boolean active(Warehouse warehouse);

}
