import java.util.ArrayList;
import java.util.List;

public class Box<T extends Fruit> {

    private List<T> fruits;
    private final float maxWeight;

    public Box() {
        maxWeight = 25;
        fruits = new ArrayList<>();
    }

    /**
     * Получаем текущий вес коробки с фруктами
     * */
    public float getBoxWeight() {
        float boxWeight = 0;
        for ( Fruit fruit : fruits ) {
            boxWeight += fruit.getWeight();
        }
        return boxWeight;
    }

    /**
     * Добавляем фрукт в коробку, если он помещается исходя из условия максимального веса коробки
     * */
    public boolean addFruit(T fruit) {
        if (getBoxWeight() + fruit.getWeight() <= maxWeight) {
            fruits.add(fruit);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Берём один фрукт из коробки
     * */
    private T getFruit() {
        if (fruits.size() > 0) {
            return fruits.get(0);
        } else {
            return null;
        }
    }

    /**
     * Удаляем фрукт из коробки
     * */
    private void dropFruit(T fruit) {
        if (fruits.contains(fruit)) {
            fruits.remove(fruit);
        }
    }

    /**
     * Сравниваем произвольные коробки фруктов по весу
     * */
    public boolean compare (Box<?> anotherBox) {
        if (this != anotherBox) {
            return (getBoxWeight() == anotherBox.getBoxWeight());
        } else {
            return false; //при сравнении коробки с собой возвращаем false
        }
    }

    /**
     * Пересыпаем фрукты в коробку из другой коробки с фруктами того же типа до достижения максимального веса
     * */
    public void fillFromAnotherBox(Box<T> anotherBox) {

        if (this == anotherBox) { return; }

        if (anotherBox.getBoxWeight() > 0) {
            T fruit = null;
            while (anotherBox.getBoxWeight() > 0) {
                fruit = anotherBox.getFruit();
                if (fruit != null && addFruit(fruit)) {
                    anotherBox.dropFruit(fruit);
                } else {
                    break;
                }
            }
        }
    }
}
