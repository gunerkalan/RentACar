package com.gunerk.rentacar.service.abstracts;
import com.gunerk.rentacar.core.utilities.Results.DataResult;
import com.gunerk.rentacar.core.utilities.Results.Result;
import com.gunerk.rentacar.dto.concrete.ColorDTO;
import java.util.List;

public interface ColorService {

    DataResult<List<ColorDTO>> getAll();

    DataResult<ColorDTO> getByColorId(Long colorId);

    Result save(ColorDTO colorDTO);

    Result update(Long id, ColorDTO colorDTO);

    Result delete(Long id);
}
