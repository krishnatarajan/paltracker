package io.pivotal.pal.tracker;


import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EnvControllerTest {

    @Test
    public void itSaysHello() throws Exception {

        String PORT_KEY = "PORT";
        String MEMORY_KEY = "MEMORY_LIMIT";
        String INSTANCE_INDEX = "CF_INSTANCE_INDEX";
        String INSTANCE_ADDR = "CF_INSTANCE_ADDR";

        String port = "8080";
        String memoryLimit = "2048";
        String instanceIndex = "1";
        String instanceAddress = "ABC";

        EnvController controller = new EnvController(instanceIndex, instanceAddress, port, memoryLimit);
        Map<String, String> envProperties = controller.getEnv();

        assertEquals(envProperties.get(PORT_KEY), port);
        assertEquals(envProperties.get(MEMORY_KEY), memoryLimit);
        assertEquals(envProperties.get(INSTANCE_INDEX), instanceIndex);
        assertEquals(envProperties.get(INSTANCE_ADDR), instanceAddress);

        //Assert.(controller.sayHello()).isEqualTo("A welcome message");
        /*
        assertThat(env.get("PORT")).isEqualTo("8675");
        assertThat(env.get("MEMORY_LIMIT")).isEqualTo("12G");
        assertThat(env.get("CF_INSTANCE_INDEX")).isEqualTo("34");
        assertThat(env.get("CF_INSTANCE_ADDR")).isEqualTo("123.sesame.street");

         */
    }
}
