package soundsystem;

import org.springframework.stereotype.Component;

@Component
public class HighSchoolRapper2Final implements CompactDisc {
    private static final String ARTIST = "김하온";
    private static final String TITLE = "붕붕";

    @Override
    public String play() {
        return String.format("Playing %s by %s", TITLE, ARTIST);
    }
}
