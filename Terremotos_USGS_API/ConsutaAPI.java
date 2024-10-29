/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.labaroratorio;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.ArrayList;

/**
 *
 * @author Erick
 */
public class Labaroratorio {

    public static void main(String[] args) {
        HttpClient client = HttpClient.newHttpClient();
        
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson")) // Reemplaza con la URL de la API que deseas usar
            .header("Accept", "application/json") // Opcional: establece el encabezado de aceptación
            .GET() // Método GET
            .build();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
            .thenApply(HttpResponse::body)
            .thenAccept(Labaroratorio::procesarRespuesta)
            //.thenAccept(System.out::println)
            .join();
    }
        private static void procesarRespuesta(String respuesta) {
        Gson gson = new Gson();
        // Supongamos que la respuesta es un objeto JSON simple
            
        JsonObject json = gson.fromJson(respuesta, JsonObject.class);
        //System.out.println(json.get("type"));
        //System.out.println(json.getAsJsonArray("features"));
        JsonArray features=json.getAsJsonArray("features");
            for (JsonElement feature : features) {
                System.out.println(feature);
            }
        //String datos =features.get(0).toString();
    }
}
