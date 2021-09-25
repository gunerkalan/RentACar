package com.gunerk.rentacar.service.abstracts;

import com.gunerk.rentacar.core.utilities.Results.DataResult;
import com.gunerk.rentacar.core.utilities.Results.Result;
import com.gunerk.rentacar.dto.concrete.RentalDTO;
import com.gunerk.rentacar.dto.concrete.RentalDetailDTO;


import java.util.List;

public interface RentalService {

    DataResult<List<RentalDTO>> getAll();

    DataResult<RentalDTO> getByRentalId(Long rentalId);

    Result save(RentalDTO rentalDTO);

    Result update(Long id, RentalDTO rentalDTO);

    Result delete(Long id);

    DataResult<List<RentalDetailDTO>> getRentalDetails(Long rentalId);
}
