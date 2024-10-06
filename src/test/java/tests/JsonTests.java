package tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Flowers;
import org.junit.jupiter.api.Test;

import java.io.InputStreamReader;
import java.io.Reader;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class JsonTests {
    private ClassLoader cl = JsonTests.class.getClassLoader();


    @Test
    void jsonFileParsingTest() throws Exception {
        try (Reader reader = new InputStreamReader(
                cl.getResourceAsStream("information.json")
        )) {
            ObjectMapper mapper = new ObjectMapper();
            Flowers flowers = mapper.readValue(reader, Flowers.class);

            assertThat(flowers.getFlowers().get(0).getNumber()).isEqualTo(1);
            assertThat(flowers.getFlowers().get(0).getName()).isEqualTo("rose");
            assertThat(flowers.getFlowers().get(0).getQuantity()).isEqualTo(5);

            assertThat(flowers.getFlowers().get(1).getNumber()).isEqualTo(2);
            assertThat(flowers.getFlowers().get(1).getName()).isEqualTo("lily");
            assertThat(flowers.getFlowers().get(1).getQuantity()).isEqualTo(10);

            assertThat(flowers.getFlowers().get(2).getNumber()).isEqualTo(3);
            assertThat(flowers.getFlowers().get(2).getName()).isEqualTo("aster");
            assertThat(flowers.getFlowers().get(2).getQuantity()).isEqualTo(15);
        }
    }
}
