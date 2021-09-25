package com.gunerk.rentacar.dto.concrete;

import com.gunerk.rentacar.dto.abstracts.IDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Color Data Transfer Object")
public class ColorListDto implements IDto {

    @NotNull
    @ApiModelProperty(required = true,value = "Color Name")
    private String colorName;
}
