import org.gradle.internal.impldep.org.junit.Test;
import ru.clevertec.service.impl.CustomArrayList;
import ru.clevertec.service.interfaces.CustomIterator;
import ru.clevertec.service.interfaces.CustomList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestCustomArrayList {
    @Test
    public static void main(String[] args) {
        Integer[] data = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        ArrayList<Integer> dataList = new ArrayList<>(List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19));
        dataList.listIterator();
        CustomArrayList<Integer> arrayListWithoutParameters = new CustomArrayList<>();
        CustomArrayList<Integer> arrayList0 = new CustomArrayList<>(20);
        CustomArrayList<Integer> arrayList1 = new CustomArrayList<>(data);

        System.out.println("arrayListWithoutParameters: ");
        printInfo(arrayListWithoutParameters);

        System.out.println("arrayList0: ");
        printInfo(arrayList0);

        System.out.println("arrayList1: ");
        printInfo(arrayList1);

        System.out.println("Next all tests been with \"arrayList0\"");


        arrayList0.addAll(dataList);

        System.out.println("Using \"addAll()\"");
        printInfo(arrayList0);


        System.out.println("Using \"add(20)\"");
        arrayList0.add(20);
        printInfo(arrayList0);

        System.out.println("Using \"remove(5)\"");
        arrayList0.remove(5);
        printInfo(arrayList0);

        System.out.println("Using \"set(5,5)\"");
        System.out.println("old element: " + arrayList0.set(5, 5));
        printInfo(arrayList0);

        System.out.println("Using \"find(2)\"");
        System.out.println(arrayList0.find(2));

        System.out.println("Using \"get(6)\"");
        System.out.println(arrayList0.get(6));

        System.out.println("Using \"clear()\"");
        arrayList0.clear();
        printInfo(arrayList0);

        System.out.println("Using \"trim()\"");
        arrayList0.trim();
        printInfo(arrayList0);

        System.out.println("Using \"setMaxSize(5)\" and \"addAll(dataList)\"");
        arrayList0.setMaxSize(5);
        arrayList0.addAll(dataList);
        printInfo(arrayList0);

        System.out.println("Using \"toArray()\"");
        System.out.println(Arrays.toString(arrayList0.toArray()));

        System.out.println("\n\nTest list Iterator");
        CustomIterator<Integer> iterator = arrayList0.getIterator();

        System.out.println("Using methods \"hasNext()\" and \"next()\"");
        while (iterator.hasNext()){
            System.out.print(iterator.next() + " ");
        }

        arrayList0.setMaxSize(0);

        System.out.println("\nUsing methods \"next() x 2\" and \"remove()\" and \"setIteratorToStart()\"");
        iterator.setIteratorToStart();
        iterator.next();
        iterator.next();
        iterator.remove();
        printInfo(arrayList0);

        System.out.println("\nUsing methods \"addBefore(10)\"");
        iterator.addBefore(10);
        printInfo(arrayList0);

        System.out.println("\nUsing methods \"addAfter(10)\"");
        iterator.addAfter(10);
        printInfo(arrayList0);


    }

    private static <T> void printInfo(CustomList<T> arrayList) {
        System.out.println("List size: " + arrayList.size());
        System.out.println(arrayList);
        System.out.println();
    }
}

