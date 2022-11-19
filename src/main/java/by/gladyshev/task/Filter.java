package by.gladyshev.task;

import by.gladyshev.Main;

import java.awt.*;

public abstract class Filter { //С прошлой лабы задача этого класса немного поменялась
    //Теперь этот класс считает R (свертку) изображения
    //Алгоритм точно такой же, как и в 4, но (След комент в строке 63)
    protected int[][] mask;
    public synchronized Color[][] filter(Color[][] pixels)
    {
        Color[][] result = new Color[pixels.length][pixels[0].length];
        for (int i = 1; i < pixels.length-1; i++) {
            for (int j = 1; j < pixels[0].length-1; j++) {
                Color[][] underMask = getUnderMask(pixels, i, j);
                result[i][j] = calculateF1(underMask, i, j);
            }
        }
        for (int i = 0; i < pixels.length; i++) {
            result[i][0] = pixels[i][0];
            result[i][result[0].length-1] = pixels[i][result[0].length-1];
        }
        for (int i = 0; i < pixels[0].length; i++) {
            result[0][i] = pixels[0][i];
            result[result.length-1][i] = pixels[result.length-1][i];
        }
        return result;
    }
    protected Color[][] getUnderMask(Color[][] pixels, int i, int j)
    {
        i = i-1;
        j = j-1;
        int js = j;
        Color[][] result = new Color[3][3];
        for (int k = 0; k < 3; k++) {
            for (int l = 0; l < 3; l++) {
                result[k][l]
                        = pixels[i][j];
                j++;
            }
            j = js;
            i++;
        }
        return result;
    }
    protected Color calculateF1(Color[][] underMask, int i, int j){
        double numerator = 0.0;
        double denominator = 0.0;
        i = i-1;
        j = j-1;
        int is = i;
        int js = j;
        for (int k = 0; k < underMask.length; k++) {
            for (int l = 0; l < underMask[0].length; l++) {
                numerator = numerator + (Main.grey[i][j].getBlue()*mask[k][l]);
                denominator = denominator + mask[k][l];
                j++;
            }
            i++;
            j = js;
        }
        //Больше мы не нормируем на сумму элементов маски
        //Во-первых это теперь тупо не нужно, во-вторых эта сумма дает 0
        //Проблематично делить
        int grey = (int) numerator; //(/denominator)
        if(grey<0)
        {
            grey = Math.abs(grey);
        }
        if(grey>255)
        {
            grey = 255;
        }
        return new Color(grey, grey, grey);
    }
}
