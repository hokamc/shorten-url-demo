package app.shorturl;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Kam
 */
class EncoderTest {
    private final Encoder encoder = new Encoder();

    @Test
    void encode0() {
        assertThat(encoder.encode(0L)).isEqualTo("000000");
    }

    @Test
    void encode1() {
        assertThat(encoder.encode(1L)).isEqualTo("000001");
    }

    @Test
    void encode61() {
        assertThat(encoder.encode(61L)).isEqualTo("00000z");
    }

    @Test
    void encode62() {
        assertThat(encoder.encode(62L)).isEqualTo("000010");
    }

    @Test
    void encode123456() {
        assertThat(encoder.encode(123456L)).isEqualTo("000W7E");
    }

    @Test
    void encode56800235583() {
        assertThat(encoder.encode(56800235583L)).isEqualTo("zzzzzz");
    }

    @Test
    void boundaryException() {
        assertThrows(IllegalArgumentException.class, () -> encoder.encode(56800235584L));
    }
}
