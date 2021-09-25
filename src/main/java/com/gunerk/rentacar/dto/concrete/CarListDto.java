package com.gunerk.rentacar.dto.concrete;

import com.gunerk.rentacar.dto.abstracts.IDto;
import com.gunerk.rentacar.entities.concrete.EngineType;
import com.gunerk.rentacar.entities.concrete.GearType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Car Data Transfer Object")
public class CarListDto implements IDto {
    @ApiModelProperty(required = true,value = "Car ID")
    private Long id;

    @NotNull
    @ApiModelProperty(required = true,value = "Engine Type")
    private EngineType engineType;

    @NotNull
    @ApiModelProperty(required = true,value = "Gear Type")
    private GearType gearType;

    @NotNull
    @ApiModelProperty(required = true,value = "Car Name")
    private String carName;

    @NotNull
    @ApiModelProperty(required = true,value = "Model Year")
    private int modelYear;

    @NotNull
    @ApiModelProperty(required = true,value = "Daily Price")
    private Float dailyPrice;

    @ApiModelProperty(required = true,value = "Descriptions")
    private String descriptions;

    @ApiModelProperty(required = true,value = "Car Brand")
    private BrandListDto brand;

    @ApiModelProperty(required = true,value = "Car Color")
    private ColorListDto color;
}
