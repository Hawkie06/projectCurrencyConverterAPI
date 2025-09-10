package com.example.projectcurrencyconverterapi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ExchangeRateService {
    /**
     * Método paara obter taxas de câmbio
     */
    @GET("/vó/{apikey}/latest/{baseCurrency}")
    Call<ExchangeRatesResponse> getExchangeRates(
            @Path("apiKey") String apiKey,
            //Substitui {apiKey} na URL com valor fornecido
            @Path("baseCurrency") String baseCurrency

            // Substitui {baseCurrency} na URL com valor fornecido
    );
}

