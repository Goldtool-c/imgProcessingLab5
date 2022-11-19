package by.gladyshev.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PixelsToFileConverter {
    private static PixelsToFileConverter instance;

    private PixelsToFileConverter(){}
    public static PixelsToFileConverter getInstance() {
        if(instance==null) {
            instance = new PixelsToFileConverter();
        }
        return instance;
    }
    public void convertAndSave(Color[][] pixels, String path) throws IOException {
        BufferedImage bufferedImage = new BufferedImage(pixels.length, pixels[0].length,
                BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < pixels.length; x++) {
            for (int y = 0; y < pixels[0].length; y++) {
                try {
                    bufferedImage.setRGB(x, y, pixels[x][y].getRGB());
                }
                catch (Exception e){
                    bufferedImage.setRGB(x, y, new Color(0,0,0).getRGB());
                }
            }
        }
        File outputfile = new File(path);
        ImageIO.write(bufferedImage, "jpg", outputfile);
    }
}
