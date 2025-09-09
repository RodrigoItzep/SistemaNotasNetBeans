package com.umg.notass.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.umg.notass.model.Asignacion;
import org.apache.hc.client5.http.classic.methods.HttpDelete;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.classic.methods.HttpPut;
import org.apache.hc.client5.http.entity.EntityBuilder;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpResponse;
import org.apache.hc.core5.http.ContentType;

import java.io.InputStream;
import java.util.List;

public class AsignacionService {
    private static final String BASE_URL = "http://localhost:8081/api/asignaciones";
    private static final ObjectMapper mapper = new ObjectMapper();

    // GET todas las asignaciones
    public List<Asignacion> getAsignaciones() throws Exception {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(BASE_URL);
            ClassicHttpResponse response = (ClassicHttpResponse) client.execute(request);
            InputStream is = response.getEntity().getContent();
            return mapper.readValue(is, new TypeReference<List<Asignacion>>() {});
        }
    }

    // POST crear asignación
    public Asignacion createAsignacion(Asignacion asignacion) throws Exception {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost request = new HttpPost(BASE_URL + "/create");
            String json = mapper.writeValueAsString(asignacion);
            request.setEntity(EntityBuilder.create()
                    .setText(json)
                    .setContentType(ContentType.APPLICATION_JSON)
                    .build());
            ClassicHttpResponse response = (ClassicHttpResponse) client.execute(request);
            InputStream is = response.getEntity().getContent();
            return mapper.readValue(is, Asignacion.class);
        }
    }

    // PUT actualizar asignación
    public Asignacion updateAsignacion(int id, Asignacion asignacion) throws Exception {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPut request = new HttpPut(BASE_URL + "/update/" + id);
            String json = mapper.writeValueAsString(asignacion);
            request.setEntity(EntityBuilder.create()
                    .setText(json)
                    .setContentType(ContentType.APPLICATION_JSON)
                    .build());
            ClassicHttpResponse response = (ClassicHttpResponse) client.execute(request);
            InputStream is = response.getEntity().getContent();
            return mapper.readValue(is, Asignacion.class);
        }
    }

    // DELETE eliminar asignación
    public String deleteAsignacion(int id) throws Exception {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpDelete request = new HttpDelete(BASE_URL + "/delete/" + id);
            ClassicHttpResponse response = (ClassicHttpResponse) client.execute(request);
            InputStream is = response.getEntity().getContent();
            return new String(is.readAllBytes());
        }
    }
}