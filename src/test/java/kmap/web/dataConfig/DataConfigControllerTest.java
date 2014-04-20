package kmap.web.dataConfig;

import kmap.web.config.WebAppConfigurationAware;
import org.junit.Test;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.*;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by msaidi on 20.04.14.
 */
public class DataConfigControllerTest extends WebAppConfigurationAware{
    @Test
    public void testGetAllSeries()  throws Exception {

        ResultActions perform = mockMvc.perform(get("/data"));
        perform
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                    .andExpect(jsonPath("$", hasSize(1000))

                    );
        }



}

