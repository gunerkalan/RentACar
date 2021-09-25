package com.gunerk.rentacar.api;

import com.gunerk.rentacar.core.utilities.Results.DataResult;
import com.gunerk.rentacar.core.utilities.Results.Result;
import com.gunerk.rentacar.dto.concrete.RentalDTO;
import com.gunerk.rentacar.service.abstracts.RentalService;
import com.gunerk.rentacar.core.utilities.Paths.ApiPaths;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@CrossOrigin
@RequestMapping(value = ApiPaths.RentalCtrl.CTRL)
public class RentalController {

    private final RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @GetMapping("/getAll")
    @ApiOperation(value = "Get All Rental Operation", response = String.class, responseContainer = "List")
    public DataResult<List<RentalDTO>> getAll(){
        return this.rentalService.getAll();
    }

    @GetMapping("/getById/{id}")
    @ApiOperation(value = "Get By Id Operation", response = RentalDTO.class)
    public DataResult<RentalDTO> getById(@PathVariable(value = "id",required = true)Long id){
        return this.rentalService.getByRentalId(id);
    }

    @PostMapping
    @ApiOperation(value = "Add Operation", response = RentalDTO.class)
    public Result addRental(@Valid @RequestBody RentalDTO rentalDTO){
        return this.rentalService.save(rentalDTO);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update Operation", response = RentalDTO.class)
    public Result updateRental(@PathVariable(value = "id",required = true)Long id, @Valid @RequestBody RentalDTO rentalDTO)
    {
        return this.rentalService.update(id,rentalDTO);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete Operation",response = Boolean.class)
    public Result deleteRental(@PathVariable(value = "id",required = true)Long id){
        return this.rentalService.delete(id);
    }
}
