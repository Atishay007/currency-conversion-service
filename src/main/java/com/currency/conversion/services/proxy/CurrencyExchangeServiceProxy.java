package com.currency.conversion.services.proxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.currency.conversion.services.dto.CurrencyConversionDTO;

@Component
//@FeignClient(name = "currency-exchange-service", url = "localhost:8001")
@FeignClient(name = "currency-exchange-service")
@RibbonClient("currency-exchange-service")
public interface CurrencyExchangeServiceProxy {

	@GetMapping("/exchangeService/from/{from}/to/{to}")
	public CurrencyConversionDTO getCurrencyExchangeValue(@PathVariable("from") String from,
			@PathVariable("to") String to);
}
