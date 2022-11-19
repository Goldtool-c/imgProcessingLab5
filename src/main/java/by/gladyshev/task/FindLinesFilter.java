package by.gladyshev.task;

public class FindLinesFilter extends FindDotsFilter {
    //Поиск линий
    //Алгоритм полностью копирует алгоритм фильтрации точек
    //Только маска другая
    //В зависимости от маски он будет фильтровать горизонтальные/вертикальные линии
    //Не тестируйте на диагональных, если она тонкая, то он ее оставит вместе с вертикальными
    //Маски в методе стр 85
    private static FindLinesFilter instance;
    private FindLinesFilter(){
        mask = new int[3][];
        mask[0] = new int[]{0, -1, 0};
        mask[1] = new int[]{0, 1, 0};
        mask[2] = new int[]{0, 0, 0};
        T = 250;
    }
    public static FindLinesFilter getInstance() {
        if(instance==null) {
            instance = new FindLinesFilter();
        }
        return instance;
    }
}
