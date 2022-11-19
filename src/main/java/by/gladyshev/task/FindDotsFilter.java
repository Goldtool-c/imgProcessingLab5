package by.gladyshev.task;

import by.gladyshev.Main;

import java.awt.*;

public class FindDotsFilter extends Filter {
    //Фильтр точек
    private static FindDotsFilter instance;
    protected int T = 150;
    protected FindDotsFilter(){
        mask = new int[3][3];
        for (int i = 0; i < mask.length; i++) {
            for (int j = 0; j < mask[0].length; j++) {
                mask[i][j] = 1;
            }
        }
        mask[1][1] = -8;
    }
    public static FindDotsFilter getInstance() {
        if(instance==null) {
            instance = new FindDotsFilter();
        }
        return instance;
    }
    public Color[][] findDots(Color[][] pixels){
        //Суть в том, что мы сравниваем занчение свертки пикселя с оригиналом(строка 33)
        Color[][] convolution = this.filter(pixels);
        Color[][] result = new Color[convolution.length][convolution[0].length];
        for (int i = 0; i < pixels.length ; i++) {
            for (int j = 0; j < pixels[0].length; j++) {
                //Если разница больше выбранного нами порога T, то мы оставляем точку нетронутой
                if(Math.abs(convolution[i][j].getBlue())>this.T){
                    result[i][j] = Main.grey[i][j];
                }
                else {//Если меньше, то заменяем ее на черный пиксель
                    result[i][j] = new Color(0,0,0);
                }
            }
        }
        //В результате получаем изображение, на котором от оригинала остались только найденные нами точки
        return result;
    }
}
