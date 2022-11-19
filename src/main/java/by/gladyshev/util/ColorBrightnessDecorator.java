package by.gladyshev.util;

import java.awt.*;

public class ColorBrightnessDecorator {
    private Color pixel;
    public ColorBrightnessDecorator(Color pixel) {
        this.pixel = pixel;
    }

    public Color getPixel() {
        return pixel;
    }

    public void setPixel(Color pixel) {
        this.pixel = pixel;
    }
    public double getBrightness()
    {
        return ((0.33*pixel.getRed()) + (0.33*pixel.getGreen()) + (0.33*pixel.getBlue()));
    }
}
