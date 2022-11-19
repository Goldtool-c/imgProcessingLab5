package by.gladyshev.task;

import java.awt.*;

public class CombinedFilter extends FindDotsFilter {
    //Комбинированный фильтр
    //Используется для улучшенного поиска краев
    private static CombinedFilter instance;
    private CombinedFilter(){
        mask = new int[3][];
        mask[0] = new int[]{1, 1, 1};
        mask[1] = new int[]{0, 0, 0};
        mask[2] = new int[]{-1, -1, -1};
        T = 250;
    }
    public static CombinedFilter getInstance() {
        if(instance==null) {
            instance = new CombinedFilter();
        }
        return instance;
    }
    public Color[][] filterPixels(Color[][] pixels)
    {
        //Вычислем градиенты по перпендикулярным маскам (см метода стр 87)
        //Gx = (z7 + z8 + z9) – (z1 + z2 + z3)
        //Gy = (z3 + z6 + z9) – (z1 + z4 + z7).
        Color[][] res = new Color[pixels.length][pixels[0].length];
        for (int i = 1; i < res.length-1; i++) {
            for (int j = 1; j < res[0].length-1; j++) {
                Color[][] underMask = getUnderMask(pixels, i, j);
                int gy = getGradientVertical(underMask);
                int gx = getGradientHorizontal(underMask);
                //Складываем модули градиентов
                int grey = Math.abs(gy)+Math.abs(gx);
                //Срезаем все, что не принадлежит (0, 255)
                if(grey>255) grey = 255;
                if(grey<0) grey = 0;
                res[i][j] = new Color(grey, grey, grey);
            }
        }
        return res;
    }
    private int getGradientHorizontal(Color[][] underMask){
        int first = 0;
        int second = 0;
        for (int i = 0; i < underMask.length; i++) {
            first = first + underMask[0][i].getBlue();
            second = second + underMask[underMask.length-1][i].getBlue();
        }
        return second - first;
    }
    private int getGradientVertical(Color[][] underMask)
    {
        int first = 0;
        int second = 0;
        for (int i = 0; i < underMask[0].length; i++) {
            first = first + underMask[i][0].getBlue();
            second = second + underMask[i][underMask[0].length-1].getBlue();
        }
        return second - first;
    }
}
