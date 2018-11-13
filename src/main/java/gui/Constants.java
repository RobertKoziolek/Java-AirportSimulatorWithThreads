package gui;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constants {
    public static final String TITLE = "Airplanes simulator!";

    public static final int WINDOW_WIDTH = 640;
    public static final int WINDOW_HEIGHT = 480;

    public static final int AIRPORT_WIDTH = 100;
    public static final int AIRPORT_HEIGHT = 84;

    public static final float PLANE_SPEED = 9f;
    public static final float BASE_FUEL = 99;
    public static final int PLANES = 33;
}
