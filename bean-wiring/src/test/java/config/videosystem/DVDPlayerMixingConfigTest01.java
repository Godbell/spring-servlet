package config.videosystem;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import config.videosystem.mixing.DVDConfig;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {DVDConfig.class})
public class DVDPlayerMixingConfigTest01 {
}
