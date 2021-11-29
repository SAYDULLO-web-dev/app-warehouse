package appwarehouse.service;

import appwarehouse.entity.Measurement;
import appwarehouse.model.MeasurementAddDto;
import appwarehouse.model.MeasurementDto;

import java.util.List;

public interface MeasurementService {


    List<MeasurementDto> getAll();

    MeasurementDto add(MeasurementAddDto dto);

    Measurement validate(Long measurementId);
}
