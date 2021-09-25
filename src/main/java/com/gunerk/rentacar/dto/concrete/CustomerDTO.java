package com.gunerk.rentacar.dto.concrete;


import com.gunerk.rentacar.dto.abstracts.IDto;
import com.gunerk.rentacar.service.constants.Messages;
import com.gunerk.rentacar.service.customValids.Tc;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Customer Data Transfer Object")
public class CustomerDTO implements IDto {

    @ApiModelProperty(required = true, value = "Customer ID")
    private Long id;

    @NotNull
    @ApiModelProperty(required = true,value = "Customer Name")
    private String customerName;

    @NotNull
    @Tc(message = Messages.invalidTc)
    @ApiModelProperty(required = true,value = "Customer Tc")
    private String customerTc;
}
