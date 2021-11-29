package appwarehouse.controller;

import appwarehouse.model.WarehouseAddDto;
import appwarehouse.model.WarehouseDto;
import appwarehouse.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/warehouse")
public class WarehouseController {

    private final WarehouseService warehouseService;

    @Autowired
    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @PostMapping("/get/list")
    public List<WarehouseDto> getList(){
        return warehouseService.getList();
    }

    @PostMapping("/add")
    public WarehouseDto add(@RequestBody WarehouseAddDto dto){
        return warehouseService.add(dto);
    }

}
