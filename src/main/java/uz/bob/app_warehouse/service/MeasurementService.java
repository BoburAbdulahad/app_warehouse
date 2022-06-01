package uz.bob.app_warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.bob.app_warehouse.repository.MeasurementRepository;
import uz.bob.app_warehouse.entity.Measurement;
import uz.bob.app_warehouse.payload.Result;

import java.util.List;
import java.util.Optional;

@Service
public class MeasurementService {

    @Autowired
    MeasurementRepository measurementRepository;

    public List<Measurement> get(){
        return measurementRepository.findAll();
    }

    public Measurement getById(Integer id){
        if (!measurementRepository.findById(id).isPresent()) {
            return new Measurement();
        }
        return measurementRepository.getReferenceById(id);
    }

    public Result addMeasurementService(Measurement measurement){
        boolean existsByName = measurementRepository.existsByName(measurement.getName());
        if (existsByName)
            return new Result("This measurement name already exist",false);
        measurementRepository.save(measurement);
        return new Result("Measurement saved",true);
    }


    public Result edit(Integer id,Measurement measurement){
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);
        if (!optionalMeasurement.isPresent()) {
            return new Result("Measurement not found",false);
        }
        Measurement editingMeasurement = optionalMeasurement.get();
        editingMeasurement.setName(measurement.getName());
        Measurement saved = measurementRepository.save(editingMeasurement);
        return new Result("Measurement edited",true,saved);
    }

    //Delete measurement
    public Result delete(Integer id){
        if (!measurementRepository.findById(id).isPresent()) {
            return new Result("Measurement not found",false);
        }
        measurementRepository.deleteById(id);
        return new Result("Measurement deleted",false);
    }
}
