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
        assertThat(encoder.encode(0L)).isEqualTo("000");
    }

    @Test
    void encode1() {
        assertThat(encoder.encode(1L)).isEqualTo("001");
    }

    @Test
    void encode61() {
        assertThat(encoder.encode(61L)).isEqualTo("00z");
    }

    @Test
    void encode62() {
        assertThat(encoder.encode(62L)).isEqualTo("010");
    }

    @Test
    void encode123456() {
        assertThat(encoder.encode(123456L)).isEqualTo("W7E");
    }

    @Test
    void encode238327() {
        assertThat(encoder.encode(238327L)).isEqualTo("zzz");
    }

    @Test
    void boundaryException() {
        assertThrows(IllegalArgumentException.class, () -> encoder.encode(238328L));
    }
}
