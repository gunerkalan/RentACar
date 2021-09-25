package com.gunerk.rentacar.api;

import com.gunerk.rentacar.core.utilities.Results.DataResult;
import com.gunerk.rentacar.core.utilities.Results.Result;
import com.gunerk.rentacar.dto.concrete.CarDTO;
import com.gunerk.rentacar.dto.concrete.CarListDto;
import com.gunerk.rentacar.entities.concrete.EngineType;
import com.gunerk.rentacar.entities.concrete.GearType;
import com.gunerk.rentacar.service.abstracts.CarService;
import com.gunerk.rentacar.core.utilities.Paths.ApiPaths;
import com.gunerk.rentacar.service.constants.Messages;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping(value = ApiPaths.CarCtrl.CTRL)
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/getAll")
    @ApiOperation(value = "Get All Car Operation", response = String.class, responseContainer = "List")
    public DataResult<List<CarListDto>> getAll(){
        return this.carService.getAll();
    }

    @GetMapping("/getById/{id}")
    @ApiOperation(value = "Get By Id Operation", response = CarDTO.class)
    public DataResult<CarDTO> getById(@PathVariable(value = "id",required = true)Long id){
        return this.carService.getByCarId(id);
    }

    @GetMapping("/getByBrandId/{id}")
    @ApiOperation(value = "Get By Brand Id Operation", response = CarDTO.class)
    public DataResult<List<CarListDto>> getByBrandId(@PathVariable(value = "id",required = true)Long id){
        return this.carService.getCarsByBrandId(id);
    }

    @GetMapping("/getByColorId/{id}")
    @ApiOperation(value = "Get By Color Id Operation", response = CarDTO.class)
    public DataResult<List<CarListDto>> getByColorId(@PathVariable(value = "id",required = true)Long id){
        return this.carService.getCarsByColorId(id);
    }

    @GetMapping("/getDieselEngineTypeCars/{name}")
    @ApiOperation(value = "Get By Diesel Engine Type Operation", response = CarDTO.class)
    public DataResult<List<CarDTO>> getDieselEngineTypeCars(@PathVariable String name){
        return this.carService.getCarsByEngineType(name);
    }

    @GetMapping("/getGasolineEngineTypeCars/{name}")
    @ApiOperation(value = "Get By Gasoline Engine Type Operation", response = CarDTO.class)
    public DataResult<List<CarDTO>> getGasolineEngineTypeCars(@PathVariable String name){
        return this.carService.getCarsByEngineType(name);
    }

    @GetMapping("/getAutomaticGearTypeCars")
    @ApiOperation(value = "Get By Automatic Gear Type Operation", response = CarDTO.class)
    public DataResult<List<CarDTO>> getAutomaticGearTypeCars(){
        return this.carService.getCarsByGearType(GearType.Otomatik);
    }

    @GetMapping("/getManuelGearTypeCars")
    @ApiOperation(value = "Get By Manuel Gear Type Operation", response = CarDTO.class)
    public DataResult<List<CarDTO>> getManuelGearTypeCars(){
        return this.carService.getCarsByGearType(GearType.Manuel);
    }

    @GetMapping("/engineTypes")
    @ApiOperation(value = "Get All Engine Types Operation", response = String.class,responseContainer = "List")
    public Map<Object,Object> getAllEngineTypes(){
       Map<Object,Object> hm = new LinkedHashMap<>();
       hm.put(Messages.listedEngineType, Arrays.asList(EngineType.values()));
       return hm;
    }

    @GetMapping("/gearTypes")
    @ApiOperation(value = "Get All Gear Types Operation", response = String.class,responseContainer = "List")
    public Map<Object,Object> getAllGearTypes(){
        Map<Object,Object> hm = new LinkedHashMap<>();
        hm.put(Messages.listedGearType, Arrays.asList(GearType.values()));
        return hm;
    }

    @PostMapping
    @ApiOperation(value = "Create Operation", response = CarDTO.class)
    public Result createCar(@Valid @RequestBody CarDTO carDTO){
        return this.carService.save(carDTO);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update Operation", response = CarDTO.class)
    public Result updateCar(@PathVariable(value = "id",required = true)Long id, @Valid @RequestBody CarDTO carDTO)
    {
        return this.carService.update(id,carDTO);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete Operation",response = Boolean.class)
    public Result deleteCar(@PathVariable(value = "id",required = true)Long id){
        return this.carService.delete(id);
    }
}
