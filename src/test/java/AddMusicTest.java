import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import pages.MainPage;
import pages.MusicPage;
import utils.User;
import utils.UserData;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddMusicTest extends BaseTest {

    private final User user = UserData.user1;
    private static MusicPage musicPage;
    private static final String music = "Oshhh";

    @BeforeEach
    void setUp() {
        LoginPage loginPage = new LoginPage();
        MainPage mainPage = loginPage.login(user);
        musicPage = mainPage.goToMusic();
        musicPage.deleteAllMyMusic();
    }

    @Test
    void addMusicTest() {
        musicPage.addMusic(music);
        musicPage.goToMyMusic();
        List<String> myMusic = musicPage.getMyMusicTitles();

//        assertTrue(musicPage.getAddedMusicTitle().toLowerCase().contains(music.toLowerCase())
//                || musicPage.getAddedMusicArtist().toLowerCase().contains(music.toLowerCase()));

        assertThat(myMusic).isNotEmpty();
        assertThat(myMusic).contains(music);
    }

    @AfterAll
    static void setDown() {
        musicPage.deleteAllMyMusic();
    }
}
