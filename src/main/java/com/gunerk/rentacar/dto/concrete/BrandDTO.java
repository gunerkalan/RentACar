package com.gunerk.rentacar.dto.concrete;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@ApiModel(value = "Brand Data Transfer Object")
@JsonIgnoreProperties(ignoreUnknown = true)
public class BrandDTO implements IDto {

    @ApiModelProperty(required = true,value = "Brand Id")
    private Long id;

    @NotNull
    @ApiModelProperty(required = true,value = "Brand Name")
    private String brandName;
}
