import ru.clevertec.service.impl.CustomLinkedList;
import ru.clevertec.service.interfaces.CustomIterator;
import ru.clevertec.service.interfaces.CustomList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestCustomLinkedList {
    public static void main(String[] args) {
        ArrayList<Integer> dataList = new ArrayList<>(List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19));

        CustomList<Integer> customList0 = new CustomLinkedList<>();
        CustomLinkedList<Integer> customList1 = new CustomLinkedList<>(dataList);

        System.out.println("customList0:");
        printInfo(customList0);

        System.out.println("customList1:");
        printInfo(customList1);

        System.out.println("Next all tests been with \"customList0\"");


        System.out.println("Using \"addAll()\"");
        customList0.addAll(dataList);
        printInfo(customList0);

        System.out.println("Using \"add(20)\"");
        customList0.add(20);
        printInfo(customList0);

        System.out.println("Using \"remove(5)\"");
        customList0.remove(5);
        printInfo(customList0);

        System.out.println("Using \"set(5,5)\"");
        System.out.println("old element: " + customList0.set(5, 5));
        printInfo(customList0);

        System.out.println("Using \"find(2)\"");
        System.out.println(customList0.find(2));

        System.out.println("Using \"get(6)\"");
        System.out.println(customList0.get(6));

        System.out.println("Using \"set(5,null)\"");
        System.out.println("old element: " + customList0.set(5, null));
        printInfo(customList0);

        System.out.println("Using \"trim()\"");
        customList0.trim();
        printInfo(customList0);

        System.out.println("Using \"clear()\"");
        customList0.clear();
        printInfo(customList0);

        System.out.println("Using \"setMaxSize(5)\" and \"addAll(dataList)\"");
        customList0.setMaxSize(5);
        customList0.addAll(dataList);
        printInfo(customList0);


        System.out.println("Using \"toArray()\"");
        System.out.println(Arrays.toString(customList0.toArray()));

        System.out.println("\n\nTest list Iterator");
        CustomIterator<Integer> iterator = customList0.getIterator();

        customList0.setMaxSize(0);

        System.out.println("Using methods \"hasNext()\" and \"next()\"");
        while (iterator.hasNext()){
            System.out.print(iterator.next() + " ");
        }

        System.out.println("\nUsing methods \"next() x 2\" and \"remove()\" and \"setIteratorToStart()\"");
        iterator.setIteratorToStart();
        iterator.next();
        iterator.next();
        iterator.remove();
        printInfo(customList0);

        System.out.println("\nUsing methods \"addBefore(10)\"");
        iterator.addBefore(10);
        printInfo(customList0);

        System.out.println("\nUsing methods \"addAfter(10)\"");
        iterator.addAfter(10);
        printInfo(customList0);


    }

    private static <T> void printInfo(CustomList<T> arrayList) {
        System.out.println("List size: " + arrayList.size());
        System.out.println(arrayList);
        System.out.println();
    }
}
