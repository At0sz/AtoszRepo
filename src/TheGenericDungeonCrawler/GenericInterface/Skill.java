package TheGenericDungeonCrawler.GenericInterface;

import TheGenericDungeonCrawler.Entities.Entity;

public interface Skill<T extends Entity> {

    void cast(T target) throws NoManaException;
}
