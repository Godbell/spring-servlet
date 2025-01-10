package config.videosystem;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import videosystem.DVDPack;
import videosystem.DVDPlayer;
import videosystem.DigitalVideoDisc;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "classpath:config/applicationContext.xml")
class DVDPlayerXmlConfigTest {
    // @Autowired
    // 예외 발생
    // XML Bean Configuration(Avengers)에서는 자동으로 id를 부여 하지 않는다.
    // @Qualifier 사용할 수 없다.
    DigitalVideoDisc dvd1;

    @Autowired
    @Qualifier("ironMan")
    DigitalVideoDisc dvd2;

    @Autowired
    @Qualifier("avengersInfinityWar")
    DigitalVideoDisc dvd3;

    @Autowired
    @Qualifier("avengersInfinityWar")
    DigitalVideoDisc dvd4;

    @Autowired
    @Qualifier("avengersAgeOfUltron")
    DigitalVideoDisc dvd5;

    @Autowired
    @Qualifier("avengersCaptainAmerica")
    DigitalVideoDisc dvd6;

    @Autowired
    @Qualifier("avengersDirectorEdition")
    DigitalVideoDisc dvd7;

    @Autowired
    DVDPack dvdPack;

    @Autowired
    @Qualifier("avengersExpansionEdition1")
    DigitalVideoDisc dvd8;

    @Autowired
    @Qualifier("avengersExpansionEdition2")
    DigitalVideoDisc dvd9;

    @Autowired
    @Qualifier("avengersExpansionEdition3")
    DigitalVideoDisc dvd10;

    @Autowired
    @Qualifier("dvdPlayer1")
    DVDPlayer dvdPlayer1;

    @Autowired
    @Qualifier("dvdPlayer2")
    DVDPlayer dvdPlayer2;

    @Autowired
    @Qualifier("dvdPlayer3")
    DVDPlayer dvdPlayer3;

    @Autowired
    @Qualifier("dvdPlayer4")
    DVDPlayer dvdPlayer4;

    @Autowired
    @Qualifier("dvdPlayer5")
    DVDPlayer dvdPlayer5;

    //////////////////////////////////////////////

    @Disabled
    @Test
    void testDVD1() {
        assertNotNull(dvd1);
    }

    @Test
    void testDVD2() {
        assertNotNull(dvd2);
    }

    @Test
    void testDVD3() {
        assertNotNull(dvd3);
    }

    @Test
    void testDVD4() {
        assertNotNull(dvd4);
    }

    @Test
    void testDVD5() {
        assertNotNull(dvd5);
    }

    @Test
    void testDVD6() {
        assertNotNull(dvd6);
    }

    @Test
    void testDVD7() {
        assertNotNull(dvd7);
    }

    @Test
    void testDVDPack() {
        assertNotNull(dvdPack);
    }

    @Test
    void testDVD8() {
        assertNotNull(dvd8);
    }

    @Test
    void testDVD9() {
        assertNotNull(dvd9);
    }

    @Test
    void testDVD10() {
        assertNotNull(dvd10);
    }

    @Test
    void testPlay1() {
        assertEquals("Playing Movie Marvel's Iron Man", dvdPlayer1.play());
    }

    @Test
    void testPlay2() {
        assertEquals("Playing Movie Marvel's Iron Man", dvdPlayer2.play());
    }

    @Test
    void testPlay3() {
        assertEquals("Playing Movie Marvel's Iron Man", dvdPlayer3.play());
    }

    @Test
    void testPlay4() {
        assertEquals("Playing Movie Marvel's Iron Man", dvdPlayer4.play());
    }

    @Test
    void testPlay5() {
        assertEquals("Playing Movie Marvel's Iron Man", dvdPlayer5.play());
    }
}
