package com.gunerk.rentacar.service.abstracts;

import com.gunerk.rentacar.core.utilities.Results.DataResult;
import com.gunerk.rentacar.core.utilities.Results.Result;
import com.gunerk.rentacar.dto.concrete.BrandDTO;

import java.util.List;

public interface BrandService {

    DataResult<List<BrandDTO>> getAll();

    DataResult<BrandDTO> getByBrandId(Long brandId);

    DataResult<BrandDTO> getByBrandName(String brandName);

    Result save(BrandDTO brandDTO);

    Result update(Long id, BrandDTO brandDTO);

    Result delete(Long id);
}
