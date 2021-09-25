package com.gunerk.rentacar.api;

import com.gunerk.rentacar.core.utilities.Results.DataResult;
import com.gunerk.rentacar.core.utilities.Results.ErrorDataResult;
import com.gunerk.rentacar.core.utilities.Results.Result;
import com.gunerk.rentacar.dto.concrete.ColorDTO;
import com.gunerk.rentacar.service.abstracts.ColorService;
import com.gunerk.rentacar.core.utilities.Paths.ApiPaths;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping(value = ApiPaths.ColorCtrl.CTRL)
public class ColorController {

    private final ColorService colorService;

    public ColorController(ColorService colorService) {
        this.colorService = colorService;
    }

    @GetMapping("/getAll")
    @ApiOperation(value = "Get All Color Operation", response = String.class, responseContainer = "List")
    public DataResult<List<ColorDTO>> getAll(){
        return this.colorService.getAll();
    }

    @GetMapping("/getById/{id}")
    @ApiOperation(value = "Get By Id Operation", response = ColorDTO.class)
    public DataResult<ColorDTO> getById(@PathVariable(value = "id",required = true)Long id){
        return this.colorService.getByColorId(id);
    }

    @PostMapping
    @ApiOperation(value = "Add Operation", response = ColorDTO.class)
    public Result addColor(@Valid @RequestBody ColorDTO colorDTO){
       return this.colorService.save(colorDTO);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update Operation", response = ColorDTO.class)
    public Result updateColor(@PathVariable(value = "id",required = true)Long id, @Valid @RequestBody ColorDTO colorDTO)
    {
        return this.colorService.update(id,colorDTO);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete Operation",response = Boolean.class)
    public Result deleteColor(@PathVariable(value = "id",required = true)Long id){
        return this.colorService.delete(id);
    }
}
