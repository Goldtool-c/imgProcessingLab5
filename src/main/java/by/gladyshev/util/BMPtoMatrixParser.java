package by.gladyshev.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BMPtoMatrixParser {
    private static BMPtoMatrixParser instance;

    private BMPtoMatrixParser(){}
    public static BMPtoMatrixParser getInstance() {
        if(instance==null) {
            instance = new BMPtoMatrixParser();
        }
        return instance;
    }
    public Color[][] parse(File bmpImage) throws IOException {
        BufferedImage image = ImageIO.read(bmpImage);
        Color[][] result = new Color[image.getWidth()][image.getHeight()];
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j <
                    image.getHeight(); j++) {
                result[i][j] = new Color(image.getRGB(i, j));
            }
        }
        return result;
    }

}
