package com.geofence.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.geofence.exception.NoDataFoundException;
import com.geofence.model.GeoFenceRequest;
import com.geofence.model.GeoFenceResponse;
import com.geofence.service.GeofenceService;
import com.geofence.testDTO.GeofenceRequestData;
import com.geofence.testDTO.GeofenceResponseData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.math.BigDecimal;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(GeofenceController.class)
class GeofenceControllerTest {

    @Autowired
    MockMvc mvc;
    @MockBean
    GeofenceService service;

    GeofenceResponseData responseData;
    GeofenceRequestData requestData;

    GeoFenceResponse res;
    GeoFenceRequest req;

    @BeforeEach
    public void setUp() {
        responseData = new GeofenceResponseData();
        requestData = new GeofenceRequestData();

        req = requestData.getGepRequestData();
        res = responseData.getGepResponseDTO();
    }

    @Test
    void get_geoFence_by_id_OK() throws Exception {
        Mockito.when(service.getGeoFence(101L)).thenReturn(res);
        mvc.perform(MockMvcRequestBuilders.get("/geofence/getGeoFence/v1/101")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.latitude").value(BigDecimal.valueOf(10000.3434)))
                .andExpect(jsonPath("$.longitude").value(BigDecimal.valueOf(2222.3444)))
                .andExpect(jsonPath("$.radius").value(BigDecimal.valueOf(12.334)));
    }

    @Test
    void get_geoFence_by_id_throws_exception() throws Exception {
        Mockito.when(service.getGeoFence(anyLong())).thenThrow(NoDataFoundException.class);
        mvc.perform(MockMvcRequestBuilders.get("/geofence/getGeoFence/v1/555")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
       Assertions.assertThrows(NoDataFoundException.class, () -> service.getGeoFence(555L));
    }

    @Test
    void create_geoFence_success() throws Exception {
        Mockito.doNothing().when(service).saveGeoFence(any());
        mvc.perform(MockMvcRequestBuilders.post("/geofence/createGeoFence/v1")
                .content(toJson(req))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
    @Test
    void update_geoFence_success() throws Exception {
        Mockito.doReturn(res).when(service).updateGeoFence(any());
        mvc.perform(MockMvcRequestBuilders.put("/geofence/updateGeoFence/v1")
                .content(toJson(req))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void del_geoFence_by_id_throws_exception() throws Exception {
        Mockito.doThrow(NoDataFoundException.class).when(service).deleteGeoFence(anyLong());
        mvc.perform(MockMvcRequestBuilders.delete("/geofence/deleteGeoFence/v1/555")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
        Assertions.assertThrows(NoDataFoundException.class, () -> service.deleteGeoFence(555L));
    }

    @Test
    void del_geoFence_by_id_sucess() throws Exception {
        Mockito.doNothing().when(service).deleteGeoFence(anyLong());
        mvc.perform(MockMvcRequestBuilders.delete("/geofence/deleteGeoFence/v1/555")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    private static String toJson(GeoFenceRequest req) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(req);
    }
}