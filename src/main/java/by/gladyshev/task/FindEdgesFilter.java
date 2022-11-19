package by.gladyshev.task;

public class FindEdgesFilter extends FindDotsFilter {
    //Поиск краев
    //Метода стр 90
    private static FindEdgesFilter instance;
    private FindEdgesFilter(){
        mask = new int[3][];
        mask[0] = new int[]{3, 3, 3};
        mask[1] = new int[]{-5, 0, 3};
        mask[2] = new int[]{-5, -5, 3};
        T = 250;
    }
    public static FindEdgesFilter getInstance() {
        if(instance==null) {
            instance = new FindEdgesFilter();
        }
        return instance;
    }
}
