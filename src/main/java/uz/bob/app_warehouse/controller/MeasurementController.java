package uz.bob.app_warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.bob.app_warehouse.entity.Measurement;
import uz.bob.app_warehouse.payload.Result;
import uz.bob.app_warehouse.service.MeasurementService;

import java.util.List;

@RestController
@RequestMapping("/measurement")
public class MeasurementController {

    @Autowired
    MeasurementService measurementService;

    @GetMapping
    public List<Measurement>get(){
        List<Measurement> measurements = measurementService.get();
        return measurements;
    }

    @GetMapping("/{id}")
    public Measurement getById(@PathVariable Integer id){
        Measurement measurement = measurementService.getById(id);
        return measurement;
    }

    @PostMapping
    public Result addMeasurementController(@RequestBody Measurement measurement){
        Result result = measurementService.addMeasurementService(measurement);
        return result;
    }

    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id,@RequestBody Measurement measurement){
        Result edit = measurementService.edit(id, measurement);
        return edit;
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        Result delete = measurementService.delete(id);
        return delete;
    }

}
