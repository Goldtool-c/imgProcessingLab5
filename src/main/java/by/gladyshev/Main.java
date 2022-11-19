package by.gladyshev;

import by.gladyshev.task.CombinedFilter;
import by.gladyshev.task.FindDotsFilter;
import by.gladyshev.task.FindEdgesFilter;
import by.gladyshev.task.FindLinesFilter;
import by.gladyshev.util.BMPtoMatrixParser;
import by.gladyshev.util.ColorBrightnessDecorator;
import by.gladyshev.util.FindGreyMinMax;
import by.gladyshev.util.PixelsToFileConverter;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Main {
    public static Color grey[][];
    public static void main(String ...args) throws IOException {
        //Фильтр обнаружения точек
        Color[][] pixels = getPixels("1.jpg");//Достаем пиксели из файла (Функцию см ниже)
        Color[][] dots = FindDotsFilter.getInstance().findDots(pixels);//Фильтруем (Описание и цель фильтра смотри в task.FindDotsFilter)
        PixelsToFileConverter.getInstance().convertAndSave(dots, "dotsFiltered.jpg");//Сохраняем
        //Фильтр обнаружения линий
        pixels = getPixels("lines.jpg");
        Color[][] lines = FindLinesFilter.getInstance().findDots(pixels);
        PixelsToFileConverter.getInstance().convertAndSave(lines, "VerticalLines.jpg");;
        //Обнаружение границ маской Кирша
        pixels = getPixels("sw1.jpg");
        Color[][] hirsh = FindEdgesFilter.getInstance().findDots(pixels);
        PixelsToFileConverter.getInstance().convertAndSave(hirsh, "hirshFiltered.jpg");
        //Комбинированный фильтр
        lines = CombinedFilter.getInstance().filterPixels(pixels);
        PixelsToFileConverter.getInstance().convertAndSave(lines, "CombinedFilter.jpg");

    }
    private static Color[][] getPixels(String path)
    {
        File file = new File(path);
        Color[][] pixels = new Color[0][0];
        try {
            pixels = BMPtoMatrixParser.getInstance().parse(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        grey = pixels;
        FindGreyMinMax.getInstance().find(grey);
        return pixels;
    }
    private static void setGrey(Color[][] pixels)
    {
        grey = new Color[pixels.length][pixels[0].length];
        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[0].length; j++) {
                int temp = (int) new ColorBrightnessDecorator(pixels[i][j]).getBrightness();
                grey[i][j] = new Color(temp, temp, temp);
            }
        }
    }
}
