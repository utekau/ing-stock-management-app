package com.ing.stackmanagement.mapper;

import com.ing.stackmanagement.model.Stock;
import com.ing.stackmanagement.model.api.StockCreate;
import com.ing.stackmanagement.model.dto.StockDTO;
import com.ing.stackmanagement.model.entity.StockEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StockMapper {

    StockMapper INSTANCE = Mappers.getMapper(StockMapper.class);

    StockDTO stockToDTO(Stock stock);
    StockEntity stockCreateToEntity(StockCreate stockCreate);
    Stock entityToStock(StockEntity stockEntity);
}
