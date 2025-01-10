package config.videosystem;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import videosystem.Avengers;
import videosystem.DVDPlayer;
import videosystem.DigitalVideoDisc;
import videosystem.IronMan;

@Configuration
public class DVDPlayerConfig {
    @Bean
    public DigitalVideoDisc avengers() {
        return new Avengers();
    }

    @Bean
    public DigitalVideoDisc ironMan() {
        return new IronMan();
    }

    @Bean
    public DVDPlayer dvdPlayer1(@Qualifier("avengers") DigitalVideoDisc dvd) {
        return new DVDPlayer(dvd);
    }

    @Bean("DVD2")
    public DVDPlayer dvdPlayer2() {
        return new DVDPlayer(ironMan());
    }
}
