package com.hugo.wallet;

import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.core.Is.is;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.ContextConfiguration;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 *
 * @author hugo
 */
@SpringBootTest
@ContextConfiguration(classes = WalletApplication.class)
//@WebMvcTest(WalletController.class)
@AutoConfigureMockMvc
public class WalletControllerTest {

    @Autowired
    private MockMvc mvc;

    //@MockBean
    //private WalletController droneController;
    @Test
    public void getDrone() throws Exception {
        /*Drone drone = new Drone();
        drone.setSerialNumber("QWE");
        drone.setModel(0);

        given(droneController.getDrone(anyString())).willReturn(drone);

        mvc.perform(get("/drone/1")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.serialNumber", is(drone.getSerialNumber())));*/
    }
}
