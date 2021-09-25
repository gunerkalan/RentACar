package com.gunerk.rentacar.service.concrete;


import com.gunerk.rentacar.core.utilities.Results.*;
import com.gunerk.rentacar.dto.concrete.ColorDTO;
import com.gunerk.rentacar.entities.concrete.Color;
import com.gunerk.rentacar.repository.concrete.ColorRepository;
import com.gunerk.rentacar.service.abstracts.ColorService;
import com.gunerk.rentacar.service.constants.Messages;

import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import springfox.documentation.annotations.Cacheable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ColorServiceImpl implements ColorService {

    private final ColorRepository colorRepository;
    private final ModelMapper modelMapper;


    public ColorServiceImpl(ColorRepository colorRepository, ModelMapper modelMapper) {
        this.colorRepository = colorRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Cacheable(value = "colors")
    public DataResult<List<ColorDTO>> getAll() {
        List<Color> colors = colorRepository.findAll();
        return new SuccessDataResult<>(Arrays.asList(modelMapper.map(colors,ColorDTO[].class)),Messages.colorListed);
    }

    @Override
    @Cacheable(value = "colorId")
    public DataResult<ColorDTO> getByColorId(Long colorId) {
        return new SuccessDataResult<>(modelMapper.map(this.colorRepository.getById(colorId),ColorDTO.class),Messages.colorListedById);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "colors"),
            @CacheEvict(value="colorId") })
    public Result save(ColorDTO colorDTO) {
        this.colorRepository.save(modelMapper.map(colorDTO,Color.class));
        return new SuccessResult(Messages.colorSavedSuccecfully);
    }



    @CachePut(value = "colors,colorId")
    public Result update(Long id, ColorDTO colorDTO) {
        Optional<Color> colorIs = colorRepository.findById(id);
        if(!colorIs.isPresent()) {
            return new ErrorResult(Messages.colorErrorUpdate);
        }
        Color color= colorIs.get();
        color.setColorName(colorDTO.getColorName());
        this.colorRepository.save(color);
        return new SuccessResult(Messages.colorUpdateSuccess);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "colors"),
            @CacheEvict(value="colorId") })
    public Result delete(Long id) {
        colorRepository.deleteById(id);
        return new SuccessResult(Messages.colorDeleteSuccess);
    }
}
