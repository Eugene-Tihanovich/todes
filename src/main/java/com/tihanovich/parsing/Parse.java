package com.tihanovich.parsing;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tihanovich.model.Model;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import java.util.HashMap;
import java.util.Map;

public class Parse {

    private final HttpClient client = HttpClient.newHttpClient();
    private final Map<Integer, Model> models = new HashMap<>();

    public Map<Integer,Model> getModels(int count) throws IOException, InterruptedException {
        for (int i = 1; i < count; i++) {
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .header("accept", "application/json")
                    .uri(URI.create("https://a.todes.by:13201/reposService/api/v1/repository/" + i))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            ObjectMapper mapper = new ObjectMapper();
            Model model = mapper.readValue(response.body(), Model.class);
            models.put(model.getId(),model);
        }
        return models;
    }
}
