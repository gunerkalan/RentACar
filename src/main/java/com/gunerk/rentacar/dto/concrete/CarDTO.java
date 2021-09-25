package com.gunerk.rentacar.dto.concrete;

import com.gunerk.rentacar.dto.abstracts.IDto;
import com.gunerk.rentacar.entities.concrete.EngineType;
import com.gunerk.rentacar.entities.concrete.GearType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Car Data Transfer Object")
public class CarDTO implements IDto {

    @ApiModelProperty(required = true,value = "Car ID")
    private Long id;

    @NotNull
    @ApiModelProperty(required = true,value = "Engine Type")
    @Enumerated(EnumType.STRING)
    private EngineType engineType;

    @NotNull
    @ApiModelProperty(required = true,value = "Gear Type")
    @Enumerated(EnumType.STRING)
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

    @NotNull
    @ApiModelProperty(required = true,value = "Brand ID")
    private Long brandId;

    @NotNull
    @ApiModelProperty(required = true,value = "Color ID")
    private Long colorId;

    @ApiModelProperty(required = true,value = "Car Brand")
    private BrandDTO brand;

    @ApiModelProperty(required = true,value = "Car Color")
    private ColorDTO color;

}
