package videosystem;

public class Avengers implements DigitalVideoDisc {
    private static final String TITLE = "Avengers";
    private static final String STUDIO = "Marvel";

    @Override
    public String play() {
        return String.format("Playing Movie %s's %s", STUDIO, TITLE);
    }
}
