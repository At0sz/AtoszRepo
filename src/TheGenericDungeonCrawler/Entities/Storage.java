package TheGenericDungeonCrawler.Entities;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Storage<T extends Item> {
    private List<T> contents = new ArrayList<>();

    public void addItem(T item) {
        contents.add(item);
    }

    public T getItem(int index) {
        return contents.get(index);
    }

    public T getBestItem(){
        return contents.stream()
                .max(Comparator.comparingInt(Item::getValue))
                .orElse(null);
    }

    public void showAll() {
        for (T item : contents) {
            System.out.println(item);
        }
    }

    public void useAll() {
        for (T item : contents) {
            item.use(); // Ez azért működik, mert tudjuk, hogy T kiterjeszti az Item-et!
        }
    }

    @Override
    public String toString() {
        return "Storage " +
                "contents:\n" + contents;
    }
}
