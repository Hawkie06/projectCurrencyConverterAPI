//Classe responsável por configurar e fornecer uma instancia retrofit
package com.example.projectcurrencyconverterapi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import com.example.projectcurrencyconverterapi.ExchangeRateService;

public class RetrofitClient {
    // URL base da API que fornece taxas de cambio

    private static final String BASE_URL = "https://v6.exchangerate-api.com/";
    // Instancia do Retrofit que será usada para fazer as requisições

    private static Retrofit retrofit = null;

    /*
    Método estatico que retorna uma instancia do serviço de taxa de cambio
    Preturn uma instancia de ExchangeRateService para realizar chamadas á API
     */
    public static ExchangeRateService getInstance(){
        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(ExchangeRateService.class);
    }
}
