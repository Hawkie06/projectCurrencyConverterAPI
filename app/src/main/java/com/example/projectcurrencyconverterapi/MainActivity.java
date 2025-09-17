package com.example.projectcurrencyconverterapi;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Map;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//importando as classes do projeto
import com.example.projectcurrencyconverterapi.ExchangeRateService;
import com.example.projectcurrencyconverterapi.RetrofitClient;


public class MainActivity extends AppCompatActivity {
    // Componentes de interface
    private Spinner spinnerMoedaOrigem, spinnerMoedaDestino;
    private EditText etValor;
    private Button btnConverter;
    private TextView tvResultado;

    //Lista de moedas suportadas
    private List<String> moedas = List.of("USD", "BRL", "EUR", "GBP", "JPY");

    // Mapa para armazenar as taxas de câmbio obtidas da API
    private Map<String, Double> taxasDecambio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //Define o layout da atividade

        //Inicializa os componentes da interface
        spinnerMoedaOrigem = findViewById(R.id.spinnerMoedaOrigem);
        spinnerMoedaDestino = findViewById(R.id.spinnerMoedaDestino);
        etValor = findViewById(R.id.etValor);
        btnConverter = findViewById(R.id.btnConverter);
        tvResultado = findViewById(R.id.tvResultado);

        //Configura os spinners com a lista de moedas
    
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, moedas);
   adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
   spinnerMoedaOrigem.setAdapter(adapter);
  spinnerMoedaDestino.setAdapter(adapter);

// Carrega taxas de câmbio da API
carregarTaxasDeCambio();

//Configura o botão de conversão
btnConverter.setOnClickListener(view -> converterMoeda());
}
 // Método para carregar taxas de câmbio da API
    private void carregarTaxasDeCambio(){
 ExchangeRateService service = RetrofitClient.getInstance();//Obtém a instância do serviço
    Call<ExchangeRatesResponse> call = service.getExchangeRates("2f5802e145f51c95e1e4de2e","USD"); //Faz a chamada da API

    //Executa a chamada de forma assíncrona
    call.enqueue(new Callback<ExchangeRatesResponse>() {
        @Override
        public void onResponse(Call<ExchangeRatesResponse> call, Response<ExchangeRatesResponse> response) {
            if (response.isSuccessful() && response.body() != null) {
                taxasDecambio = response.body().getConversionRates();// Armazena as taxas de câmbio
            }
            else {
                 tvResultado.setText("Erro ao carregar taxas de câmbio");
            }
        }

       @Override
        public void onFailure(Call<ExchangeRatesResponse>call, Throwable t ){

            tvResultado.setText("Erro ns conexão");
            return;
       }


    });

}
    //Método para converter o valor de uma moeda para a outra
    private void converterMoeda(){
        String valorTexto = etValor.getText().toString();//Obtém valor digitado pelo usuário

        // Verificar se o valor é valido
        if (valorTexto.isEmpty()){
            Toast.makeText(this, "Digite um valor válido", Toast.LENGTH_SHORT).show();
            return;
        }
        String moedaOrigem = spinnerMoedaOrigem.getSelectedItem().toString();//Obtém a moeda de origem
        String moedaDestino = spinnerMoedaDestino.getSelectedItem().toString();//Obtém a moeda de destino
        double valor = Double.parseDouble(valorTexto);

        //verifica se as taxas de câmbio estão disponíveis
        if (taxasDecambio == null || !taxasDecambio.containsKey(moedaOrigem) || taxasDecambio.containsKey(moedaDestino)){
            tvResultado.setText("Taxas de câmbio indisponíveis");
            return;

        }

    }
 }
