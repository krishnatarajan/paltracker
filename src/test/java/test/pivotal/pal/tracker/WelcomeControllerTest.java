package test.pivotal.pal.tracker;


import io.pivotal.pal.tracker.WelcomeController;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WelcomeControllerTest {

    @Test
    public void itSaysHello() throws Exception {
        WelcomeController controller = new WelcomeController("A welcome message");
        assertEquals(controller.sayHello(), "A welcome message");
        //Assert.(controller.sayHello()).isEqualTo("A welcome message");
        /*
        assertThat(env.get("PORT")).isEqualTo("8675");
        assertThat(env.get("MEMORY_LIMIT")).isEqualTo("12G");
        assertThat(env.get("CF_INSTANCE_INDEX")).isEqualTo("34");
        assertThat(env.get("CF_INSTANCE_ADDR")).isEqualTo("123.sesame.street");

         */
    }
}
