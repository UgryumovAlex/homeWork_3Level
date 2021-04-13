import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
   1. Написать метод, который меняет два элемента массива местами.(массив может быть любого ссылочного типа);
   2. Написать метод, который преобразует массив в ArrayList;
   3. Большая задача:
        a. Есть классы Fruit -> Apple, Orange;(больше фруктов не надо)
        b. Класс Box в который можно складывать фрукты, коробки условно сортируются по типу фрукта,
           поэтому в одну коробку нельзя сложить и яблоки, и апельсины;
        c. Для хранения фруктов внутри коробки можете использовать ArrayList;
        d. Сделать метод getWeight() который высчитывает вес коробки, зная количество фруктов и вес одного фрукта
           (вес яблока - 1.0f, апельсина - 1.5f, не важно в каких это единицах);
        e. Внутри класса коробка сделать метод compare, который позволяет сравнить текущую коробку с той,
           которую подадут в compare в качестве параметра, true - если их веса равны, false в противном
           случае(коробки с яблоками мы можем сравнивать с коробками с апельсинами);
        f. Написать метод, который позволяет пересыпать фрукты из текущей коробки в другую коробку(помним про сортировку фруктов,
           нельзя яблоки высыпать в коробку с апельсинами), соответственно в текущей коробке фруктов не остается, а в
           другую перекидываются объекты, которые были в этой коробке;
        g. Не забываем про метод добавления фрукта в коробку.
*/
public class homeWork {
    public static void main(String[] args) {

        //   Задание 1  -------------------------------------------
        String[] strs = {"Яблоко", "Груша", "Персик", "Виноград", "Слива"};

        System.out.println("Исходный массив строк : ");
        System.out.println(Arrays.toString(strs));

        exchangeElems(strs, 1,3);

        System.out.println("Поменяли местами элементы 1 и 3 :");
        System.out.println(Arrays.toString(strs));

        System.out.println("");

        Integer[] ints = {10, 100, 1000, 10000, 10000};

        System.out.println("Исходный массив чисел : ");
        System.out.println(Arrays.toString(ints));

        exchangeElems(ints, 2, 4);

        System.out.println("Поменяли местами элементы 2 и 4 : ");
        System.out.println(Arrays.toString(ints));

        System.out.println("");
        //-------------------------------------------------------------

        //  Задание 2 -------------------------------------------------
        List<String> strsList = arrToList(strs);
        System.out.println("Массив строк сконвертировали в ArrayList");
        System.out.println(Arrays.toString(strsList.toArray()));
        System.out.println("");

        List<Integer> intsList = arrToList(ints);
        System.out.println("Массив чисел сконвертировали в ArrayList");
        System.out.println(Arrays.toString(intsList.toArray()));
        System.out.println("");
        //--------------------------------------------------------------

        //  Задание 3
        Box<Apple> boxForApples = new Box<>();
        Box<Orange> boxForOranges = new Box<>();

        Box<Apple> boxForApples2 = new Box<>();
        Box<Orange> boxForOranges2 = new Box<>();

        for (int i=0; i < 15; i++) { //Добавим фруктов в каждый из ящиков
            boxForApples.addFruit(new Apple());
            boxForOranges.addFruit(new Orange());

            boxForApples2.addFruit(new Apple());
            if (i < 10) {
                boxForOranges2.addFruit(new Orange());
            }
        }

        System.out.println("Вес коробки с яблоками : " +boxForApples.getBoxWeight());
        System.out.println("Вес коробки с апельсинами : " +boxForOranges.getBoxWeight());
        System.out.println("Результат сравнения коробок " + boxForApples.compare(boxForOranges));
        System.out.println("");

        System.out.println("Вес коробки с яблоками №2 : " +boxForApples2.getBoxWeight());
        System.out.println("Вес коробки с апельсинами №2 : " +boxForOranges2.getBoxWeight());
        System.out.println("Результат сравнения коробок №2 " + boxForApples2.compare(boxForOranges2));
        System.out.println("");

        System.out.println("Результат сравнения коробок с яблоками " + boxForApples2.compare(boxForApples));
        System.out.println("Результат сравнения коробок с апельсинами " + boxForOranges.compare(boxForOranges2));
        System.out.println("");

        boxForApples.fillFromAnotherBox(boxForApples2);
        System.out.println("Пересыпали яблоки из коробки №2 в коробку №1, текущий вес коробки №1 " + boxForApples.getBoxWeight() +
                           ", коробки №2 " + boxForApples2.getBoxWeight());

        boxForOranges2.fillFromAnotherBox(boxForOranges);
        System.out.println("Пересыпали апельсины из коробки №1 в коробку №2, текущий вес коробки №1 " + boxForOranges.getBoxWeight() +
                ", коробки №2 " + boxForOranges2.getBoxWeight());
    }

    /**
     * Метод меняет два элемента массива местами.(массив может быть любого ссылочного типа);
     * */
    private static <T> void exchangeElems(T[] elems, int first, int second) {
        try {
            T temp = elems[second];
            elems[second] = elems[first];
            elems[first] = temp;
        } catch(ArrayIndexOutOfBoundsException e) {
            System.out.println("Некорректно указаны параметры перестановки элементов массива");
        }
    }

    /**
     * метод преобразует массив в ArrayList
     * */
    private static <T> List<T> arrToList(T[] arr) {
        List<T> arrList = new ArrayList<>();

        for (int i=0; i< arr.length; i++) {
            arrList.add(arr[i]);
        }
        return arrList;
    }
}
