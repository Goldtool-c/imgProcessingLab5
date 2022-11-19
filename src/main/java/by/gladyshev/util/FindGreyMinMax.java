package by.gladyshev.util;

import java.awt.*;

public class FindGreyMinMax {
    private static FindGreyMinMax instance;
    private FindGreyMinMax() {
    }
    public static FindGreyMinMax getInstance() {
        if(instance==null) {
            instance = new FindGreyMinMax();
        }
        return instance;
    }
    public void find(Color[][] pixels)
    {
        int max=0;
        int min = pixels[0][0].getBlue();
        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[0].length; j++) {
                if(pixels[i][j].getBlue()>max)
                {
                    max = pixels[i][j].getBlue();
                }
                if(pixels[i][j].getBlue()<min)
                {
                    min = pixels[i][j].getBlue();
                }
            }
        }
        GreyMinMaxContext.setMax(max);
        GreyMinMaxContext.setMin(min);
    }
}
