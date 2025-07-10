package com.educacionit.limpiezait.servicio;

import com.educacionit.limpiezait.dominio.Usuario;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

public class UserRandomService {



    private final RestTemplate restTemplate;
    private static final String BASE_URL = "https://randomuser.me/api/";
    private Gson gson = new Gson();

    public UserRandomService() {
        this.restTemplate = new RestTemplate();
    }


    public Usuario getRandoUser(){
        String json = this.restTemplate.getForObject(this.BASE_URL, String.class);
        Usuario usuario = new Usuario();

        System.out.println("Iniciando getRandoUser()...");

        if(json != null){
            Map<String, Object> responseMap = gson.fromJson(json, Map.class);
            System.out.println("RESPONSE-MAP : " + responseMap);

            List<Map<String, Object>> result = (List<Map<String, Object>>) responseMap.get("results");
            System.out.println("RESULT-MAP : " + result);

            JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
            JsonObject user = jsonObject.getAsJsonArray("results").get(0).getAsJsonObject();
            JsonObject name = user.getAsJsonObject("name");

            usuario.setNombre(name.get("first").getAsString());
            usuario.setApellido(name.get("last").getAsString());
            usuario.setEmail(user.get("email").getAsString());
        }

        //TODO: Deserializar el json para obtener datos de nombre, apellido y email
        // , para luego  generar mi objeto Usuario de negocio,
        // luego llamar este metodo desde el controller

        return usuario;
    }
}
