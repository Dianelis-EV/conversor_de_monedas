package Logica;

import Model.Monedas;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.TreeMap;

public class Convertir {
    private String apiKey = "ef674a4214f8decfed9a864b";
    private Gson gson = new Gson();

    public TreeMap<String, String> clave_de_monedads() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request =  HttpRequest.newBuilder().uri(URI.create("https://v6.exchangerate-api.com/v6/"+apiKey+"/codes"))
                .build();
        TreeMap<String,String> listado_monedas = new TreeMap<>();
        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
        Monedas moneda = gson.fromJson((String) response.body(), Monedas.class);
        for (Map.Entry<String, String> elemento : moneda.supported_codes().entrySet()) {
            listado_monedas.put(elemento.getKey(),elemento.getValue());
        }
        return listado_monedas;
    }

    public TreeMap<String,Double> listado_monedas(String moneda) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request =  HttpRequest.newBuilder().uri(URI.create("https://v6.exchangerate-api.com/v6/"+apiKey+"/latest/"+moneda))
                .build();
        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
        Todoscambios money = gson.fromJson((String) response.body(), Todoscambios.class);
        return money.conversion_rates();
    }
    public double cambiode2monedas(String moneda1, String moneda2, double cantidad)throws IOException, InterruptedException{
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request =  HttpRequest.newBuilder().uri(URI.create("https://v6.exchangerate-api.com/v6/"+apiKey+"/pair/"+moneda1+"/"+moneda2))
                .build();
        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
        Cambio moneda = gson.fromJson((String) response.body(), Cambio.class);
        return moneda.conversion_rate() * cantidad;

    }


}
