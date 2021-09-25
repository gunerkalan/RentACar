package com.gunerk.rentacar.dto.dtoTransfer.concrete;

import com.gunerk.rentacar.dto.concrete.ColorDTO;
import com.gunerk.rentacar.dto.dtoTransfer.abstracts.IColorDtoTransfer;
import com.gunerk.rentacar.entities.concrete.Color;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ColorDtoTransfer implements IColorDtoTransfer {
    @Override
    public Color colorDtoToColor(ColorDTO colorDTO) {

        if(colorDTO==null)
        {
            return null;
        }

        Color color = new Color();
        color.setId(colorDTO.getId());
        color.setColorName(colorDTO.getColorName());

        return color;
    }

    @Override
    public ColorDTO colorToColorDto(Color color) {
        if(color==null)
        {
            return null;
        }

        ColorDTO colorDTO = new ColorDTO();
        colorDTO.setId(color.getId());
        colorDTO.setColorName(color.getColorName());

        return colorDTO;
    }

    @Override
    public List<Color> colorListToColorDtoList(List<ColorDTO> colorDTOS) {

        if(colorDTOS==null)
        {
            return null;
        }

        List<Color> list = new ArrayList<Color>(colorDTOS.size());
        for(ColorDTO colorDTO : colorDTOS){
            list.add(colorDtoToColor(colorDTO));
        }

        return list;
    }

    @Override
    public List<ColorDTO> colorDtoListToColorListDto(List<Color> colors) {
        if(colors==null){
            return null;
        }

        List<ColorDTO> list = new ArrayList<ColorDTO>(colors.size());
        for(Color color: colors){
            list.add(colorToColorDto(color));
        }

        return list;
    }
}
