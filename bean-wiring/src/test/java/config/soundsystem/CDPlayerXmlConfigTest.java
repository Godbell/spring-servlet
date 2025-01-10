package config.soundsystem;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import soundsystem.CDPlayer;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {CDPlayerConfig.class})
class CDPlayerXmlConfigTest {
    @Autowired
    CDPlayer cdPlayer;

    @Test
    void testCDPlayerNotNull() {
        assertNotNull(cdPlayer);
    }

    @Test
    void testPlay() {
        assertEquals("Playing 붕붕 by 김하온", cdPlayer.play());
    }
}
