package com.gunerk.rentacar.service.concrete;

import com.gunerk.rentacar.core.utilities.Results.*;
import com.gunerk.rentacar.dto.concrete.BrandDTO;
import com.gunerk.rentacar.entities.concrete.Brand;
import com.gunerk.rentacar.repository.concrete.BrandRepository;
import com.gunerk.rentacar.service.abstracts.BrandService;
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
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;
    private final ModelMapper modelMapper;

    public BrandServiceImpl(BrandRepository brandRepository, ModelMapper modelMapper) {
        this.brandRepository = brandRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    @Cacheable(value = "brands")
    public DataResult<List<BrandDTO>> getAll() {
        List<Brand> brands = this.brandRepository.findAll();
        return new SuccessDataResult<>(Arrays.asList(modelMapper.map(brands,BrandDTO[].class)),Messages.brandListed);
    }

    @Override
    @Cacheable(value = "brandId")
    public DataResult<BrandDTO> getByBrandId(Long brandId) {
        return new SuccessDataResult<>(modelMapper.map(this.brandRepository.getById(brandId),BrandDTO.class),Messages.brandListedById);
    }

    @Override
    @Cacheable(value = "brandName")
    public DataResult<BrandDTO> getByBrandName(String brandName) {
        List<Brand> brands = this.brandRepository.findAll();
        Optional<Brand> brand = brands.stream().filter(item->item.getBrandName().equals(brandName)).findFirst();
        if(brand.isEmpty()){
            return new ErrorDataResult<>(Messages.brandGetNameError);
        }
        return new SuccessDataResult<>(modelMapper.map(brand.get(),BrandDTO.class),Messages.brandListedByName);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "brands",allEntries = true),
            @CacheEvict(value = "brandId",allEntries = true),
            @CacheEvict(value="brandName",allEntries = true) })
    public Result save(BrandDTO brandDTO) {
        this.brandRepository.save(modelMapper.map(brandDTO,Brand.class));
        return new SuccessResult(Messages.brandSavedSuccecfully);
    }

    @Override
    @CachePut(value = "brands,brandId,brandName")
    public Result update(Long id, BrandDTO brandDTO) {
        Optional<Brand> brandIs = brandRepository.findById(id);
        if(brandIs.isEmpty()){
            return new ErrorResult(Messages.brandErrorUpdate);
        }
        Brand brand= brandIs.get();
        brand.setBrandName(brandDTO.getBrandName());
        this.brandRepository.save(brand);
        return new SuccessResult(Messages.brandUpdateSuccess);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "brands",allEntries = true),
            @CacheEvict(value = "brandId",allEntries = true),
            @CacheEvict(value="brandName",allEntries = true) })
    public Result delete(Long id) {
        this.brandRepository.deleteById(id);
        return new SuccessResult(Messages.brandDeleteSuccess);
    }
}
