import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Conversor {

    public float Convertir(int numero,float cantidad) {
        String monedaBase=obtenerMonedaBase(numero);
        String monedaDestino=obtenerMonedaDestino(numero);

        String url_str = "https://v6.exchangerate-api.com/v6/da7ee8ed64ecec0065546d23/latest/"+monedaBase;
        URI direccion =URI.create(url_str);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            Moneda moneda = new Gson().fromJson(response.body(),Moneda.class);

            return cantidad * moneda.conversion_rates().get(monedaDestino);
        } catch (Exception e) {
            throw new RuntimeException("Ha habido un error");
        }
    }


    private String obtenerMonedaBase(int opcion) {
        return switch (opcion) {
            case 1, 3, 5 -> "USD";
            case 2 -> "ARS";
            case 4 -> "BRL";
            case 6 -> "UYU";
            default -> throw new IllegalArgumentException("Opción inválida");
        };
    }

    private String obtenerMonedaDestino(int opcion) {
        return switch (opcion) {
            case 1 -> "ARS";
            case 2,4,6 -> "USD";
            case 3 -> "BRL";
            case 5 -> "UYU";
            default -> throw new IllegalArgumentException("Opción inválida");
        };
    }
}
