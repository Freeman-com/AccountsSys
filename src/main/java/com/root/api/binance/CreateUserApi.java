package com.root.api.binance;

import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiRestClient;

public class CreateUserApi {
    BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance("API-KEY", "SECRET");
    BinanceApiRestClient client = factory.newRestClient();

}
