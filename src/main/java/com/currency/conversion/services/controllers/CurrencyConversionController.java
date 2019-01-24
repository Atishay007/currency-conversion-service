package com.currency.conversion.services.controllers;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.currency.conversion.services.dto.CurrencyConversionDTO;
import com.currency.conversion.services.proxy.CurrencyExchangeServiceProxy;

@RestController
public class CurrencyConversionController {

	private static final Logger LOGGER=LoggerFactory.getLogger(CurrencyConversionController.class);
	
	@Autowired
	private CurrencyExchangeServiceProxy exchServiceProxy;

	@GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionDTO getCurrencyConversionDetails(@PathVariable String from, @PathVariable String to,
			@PathVariable(name = "quantity") BigDecimal qty) {

		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);

		// We can use RestTemplate to test HTTP based restful web services, it doesn’t
		// support HTTPS protocol.

		// NOTE: Fields name in CurrencyConversionDTO should match with
		// ExchangeServiceDTO otherwise there will be no mapping.
		ResponseEntity<CurrencyConversionDTO> responseEntity = new RestTemplate().getForEntity(
				"http://localhost:8001/exchangeService/from/{from}/to/{to}", CurrencyConversionDTO.class, uriVariables);

		// Getting currency conversion details from responseEntity.
		CurrencyConversionDTO currencyConvResp = responseEntity.getBody();

		return new CurrencyConversionDTO(currencyConvResp.getId(), currencyConvResp.getFrom(), currencyConvResp.getTo(),
				qty, currencyConvResp.getPort(), currencyConvResp.getConversionMultiple(),
				qty.multiply(currencyConvResp.getConversionMultiple()));
	}

	/**
	 * Getting details Using Feign Client which is of Netflix.
	 * 
	 * @param from
	 * @param to
	 * @param qty
	 * @return CurrencyConversionDTO
	 */
	@GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionDTO getCurrencyConversionDetailsUsingFeign(@PathVariable String from,
			@PathVariable String to, @PathVariable(name = "quantity") BigDecimal qty) {

		// Getting currency conversion details from responseEntity.
		CurrencyConversionDTO currencyConvResp = exchServiceProxy.getCurrencyExchangeValue(from, to);

		LOGGER.info("{}",currencyConvResp);
		
		return new CurrencyConversionDTO(currencyConvResp.getId(), currencyConvResp.getFrom(), currencyConvResp.getTo(),
				qty, currencyConvResp.getPort(), currencyConvResp.getConversionMultiple(),
				qty.multiply(currencyConvResp.getConversionMultiple()));
	}

}
