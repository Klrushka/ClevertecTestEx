import ru.clevertec.service.impl.CustomLinkedList;
import ru.clevertec.service.interfaces.CustomIterator;
import ru.clevertec.service.interfaces.CustomList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestCustomLinkedList {
    public static void main(String[] args) {
        CustomList<Integer> customList = new CustomLinkedList<>();
        List dataList = new ArrayList(List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));



    }

    private static <T> void printInfo(CustomList<T> arrayList) {
        System.out.println("List size: " + arrayList.size());
        System.out.println(arrayList);
        System.out.println();
    }
}
