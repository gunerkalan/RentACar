package com.gunerk.rentacar.dto.concrete;


import com.gunerk.rentacar.dto.abstracts.IDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Car Image Date Transfer Object")
public class CarImageDTO implements IDto {

    private Long id;

    @NotNull
    @ApiModelProperty(required = true,value = "Image Path")
    private String imagePath;

    @NotNull
    @ApiModelProperty(required = true,value = "Image Data")
    private Date imageDate;

    @NotNull
    @ApiModelProperty(required = true,value = "Image Data")
    private Long carId;

    @ApiModelProperty(required = true,value = "Car CarImage")
    private CarDTO car;
}
