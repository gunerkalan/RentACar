package com.gunerk.rentacar.api;

import com.gunerk.rentacar.core.utilities.Results.DataResult;
import com.gunerk.rentacar.core.utilities.Results.Result;
import com.gunerk.rentacar.dto.concrete.BrandDTO;
import com.gunerk.rentacar.service.abstracts.BrandService;
import com.gunerk.rentacar.core.utilities.Paths.ApiPaths;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping(value = ApiPaths.BrandCtrl.CTRL)
public class BrandController {

    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }


    @GetMapping("/getAll")
    @ApiOperation(value = "Get All Brand Operation", response = String.class, responseContainer = "List")
    public DataResult<List<BrandDTO>> getAll(){
        return this.brandService.getAll();
    }

    @GetMapping("/getById/{id}")
    @ApiOperation(value = "Get By Id Operation", response = BrandDTO.class)
    public DataResult<BrandDTO> getById(@PathVariable(value = "id",required = true)Long id){
        return this.brandService.getByBrandId(id);
    }

    @GetMapping("/getByBrandName/{name}")
    @ApiOperation(value = "Get By Brand Name Operation", response = String.class,responseContainer = "List")
    public DataResult<BrandDTO> getByBrandName(@PathVariable String name){
        return this.brandService.getByBrandName(name);
    }

    @PostMapping
    @ApiOperation(value = "Add Operation", response = BrandDTO.class)
    public Result addBrand(@Valid @RequestBody BrandDTO brandDTO){
       return this.brandService.save(brandDTO);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update Operation", response = BrandDTO.class)
    public Result updateBrand(@PathVariable(value = "id",required = true)Long id, @Valid @RequestBody BrandDTO brandDTO)
    {
        return this.brandService.update(id,brandDTO);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete Operation",response = Boolean.class)
    public Result deleteBrand(@PathVariable(value = "id",required = true)Long id){
        return this.brandService.delete(id);
    }
}
