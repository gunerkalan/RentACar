package com.gunerk.rentacar.dto.dtoTransfer.abstracts;

import com.gunerk.rentacar.dto.concrete.ColorDTO;
import com.gunerk.rentacar.entities.concrete.Color;

import java.util.List;

public interface IColorDtoTransfer {

    Color colorDtoToColor(ColorDTO colorDTO);
    ColorDTO colorToColorDto(Color color);
    List<Color> colorListToColorDtoList(List<ColorDTO> colorDTOS);
    List<ColorDTO> colorDtoListToColorListDto(List<Color> colors);
}
