
//Modelo de dados (DTO - Data Transfer Object) que representa a resposta json da API
//Json
package com.example.projectcurrencyconverterapi;

import java.util.HashMap; // Importa a classe Map da biblioteca Java, usada para armazenar pares da chave-valor
import java.util.Map;  // Importa a classe Map da biblioteca Java, usada para armazenar pares chave-valor

public class ExchangeRatesResponse {
    // declara um mapa que armazena taxas de conversão em moedas. (ex. USD, EUR, GBP)
    // A chave (String) representa o código da Moeda e o valor (Double) representa a taxa de conversão.

    private Map<String, Double> conversion_rates = new HashMap<>();
    // Metodo publico para acessar as taxas de conversão
    // Retorno o mapa de taxas de conversão

    public Map<String, Double> getConversionRates() {
        return conversion_rates;
    }
}