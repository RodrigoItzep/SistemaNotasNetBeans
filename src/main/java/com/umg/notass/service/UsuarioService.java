package com.umg.notass.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.umg.notass.model.Usuario;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.EntityBuilder;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpResponse;
import org.apache.hc.core5.http.ContentType;

import java.io.InputStream;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

public class UsuarioService {
    private static final String BASE_URL = "http://localhost:8081/api/usuarios";
    private static final ObjectMapper mapper = new ObjectMapper();

    public Usuario login(String username, String password) throws Exception {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost request = new HttpPost(BASE_URL + "/login");

            // Hashear la contraseña con SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = digest.digest(password.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashedBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            String hashedPassword = hexString.toString();

            // Crear cuerpo de la solicitud
            Map<String, String> loginData = new HashMap<>();
            loginData.put("username", username);
            loginData.put("password", hashedPassword);
            String json = mapper.writeValueAsString(loginData);

            request.setEntity(EntityBuilder.create()
                    .setText(json)
                    .setContentType(ContentType.APPLICATION_JSON)
                    .build());

            ClassicHttpResponse response = (ClassicHttpResponse) client.execute(request);
            int statusCode = response.getCode();
            InputStream is = response.getEntity().getContent();

            if (statusCode == 200) {
                return mapper.readValue(is, Usuario.class);
            } else {
                throw new Exception("Error de autenticación: " + new String(is.readAllBytes()));
            }
        }
    }
}