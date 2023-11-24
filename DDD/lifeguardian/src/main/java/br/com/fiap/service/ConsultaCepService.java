package br.com.fiap.service;

import br.com.fiap.entity.EnderecoViaCep;
import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaCepService {

    public EnderecoViaCep buscaEndereco(String cep) {
        URI endereco = URI.create("https://viacep.com.br/ws/" + cep.replace("-", "") + "/json/");

        HttpRequest request = HttpRequest.newBuilder()
                .uri(endereco)
                .build();

        try {
            HttpResponse<String> response = HttpClient.newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofString());
            return new Gson().fromJson(response.body(), EnderecoViaCep.class);
        } catch (Exception e) {
            throw new RuntimeException("Não consegui obter o endereço a partir deste cep", e);
        }
    }
}
