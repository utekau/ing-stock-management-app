package com.ing.stackmanagement.mapper;

import com.ing.stackmanagement.model.StockExchange;
import com.ing.stackmanagement.model.dto.StockExchangeDTO;
import com.ing.stackmanagement.model.entity.StockExchangeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StockExchangeMapper {

    StockExchangeMapper INSTANCE = Mappers.getMapper(StockExchangeMapper.class);

    StockExchange entityToStockExchange(StockExchangeEntity stockExchangeEntity);
    StockExchangeDTO stockExchangeToDTO(StockExchange stockExchange);
}
