package appwarehouse.service.imlp;

import appwarehouse.entity.Category;
import appwarehouse.entity.Measurement;
import appwarehouse.helpers.MapstructMapper;
import appwarehouse.helpers.Utils;
import appwarehouse.model.MeasurementAddDto;
import appwarehouse.model.MeasurementDto;
import appwarehouse.repository.MeasurementRepo;
import appwarehouse.service.MeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MeasurementServiceImpl implements MeasurementService {

    private final MeasurementRepo measurementRepo;
    private final MapstructMapper mapstructMapper;

    @Autowired
    public MeasurementServiceImpl(MeasurementRepo measurementRepo, MapstructMapper mapstructMapper) {
        this.measurementRepo = measurementRepo;
        this.mapstructMapper = mapstructMapper;
    }

    @Override
    public List<MeasurementDto> getAll() {
        List<Measurement> list= measurementRepo.findAll();
        return mapstructMapper.toMeasurementDto(list);
    }

    @Override
    public MeasurementDto add(MeasurementAddDto dto) {
        if (Utils.isEmpty(dto.getName())) {
            throw new RuntimeException("Measurement's name shouldn't be empty!");
        } else {
            Optional<Measurement> measurementOptional = measurementRepo.findByName(dto.getName());
            if (measurementOptional.isPresent()) {
                throw new RuntimeException("This name is already exist.");
            }
        }
        Measurement measurement = mapstructMapper.toMeasurement(dto);
        Measurement savedMeasurement=measurementRepo.save(measurement);
        return mapstructMapper.toMeasurementDto(savedMeasurement);
    }

    @Override
    public Measurement validate(Long measurementId) {
        Optional<Measurement> measurementOptional = measurementRepo.findById(measurementId);
        if (!measurementOptional.isPresent()){
            throw new RuntimeException("Measurement ID = " + measurementId + " not found.");
        }
        Measurement measurement=measurementOptional.get();
        if (!measurement.getActive()) {
            throw new RuntimeException("Measuremen ID = " + measurementId + " is inactive.");
        }
        return measurement;
    }
}
