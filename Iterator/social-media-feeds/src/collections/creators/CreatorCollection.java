package collections.creators;

import platform.users.Creator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class CreatorCollection<T extends Creator> {
    private final Map<String, T> creatorMap;

    public CreatorCollection() {
        this.creatorMap = new HashMap<>();
    }

    public void add(T creator) {
        creatorMap.put(creator.getName(), creator);
    }

    public T get(String name) {
        return creatorMap.getOrDefault(name, null);
    }

    public boolean containsKey(String name) {return creatorMap.containsKey(name);}

    public List<T> getAll() {return new ArrayList<T>(creatorMap.values());}

}
