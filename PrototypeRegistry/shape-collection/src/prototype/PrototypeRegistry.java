package prototype;

import shapes.Circle;
import shapes.Rectangle;
import shapes.Shape;
import shapes.Triangle;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrototypeRegistry {

    private Map<String, List<Shape>> items;
    private final String[] colors = {"RED", "GREEN", "BLUE"};

    public PrototypeRegistry () {
        items = new HashMap<>();
        items.put("circle", new ArrayList<>());
        items.put("rectangle", new ArrayList<>());
        items.put("triangle", new ArrayList<>());
    }

    public Shape getById(String type, int id) {
        for (Shape item : items.get(type)) {
            if (item.getId() == id)
                return item.copy();
        }
        return null;
    }

    public Shape getByColor(String type, String color) throws Exception {
        for (Shape item : items.get(type)) {
            if (item.getColor().equals(color))
                return item.copy();
        }
        Shape ret = switch (type.toLowerCase()) {
            case "circle" -> new Circle(0, 0, color, 5);
            case "rectangle" -> new Rectangle(0, 0, color, 5, 10);
            case "triangle" -> new Triangle(0, 0, color, 3, 4);
            default -> throw new Exception("Unsupported type");
        };
        items.get(type).add(ret);
        return ret;
    }

    public Shape getByName(String name) throws Exception {
        name = name.toLowerCase();
        Shape ret;
        if (items.get(name).isEmpty()) {
            ret = switch (name) {
                case "circle" -> new Circle(0, 0, "RED", 5);
                case "rectangle" -> new Rectangle(0, 0, "RED", 5, 10);
                case "triangle" -> new Triangle(0, 0, "RED", 3, 4);
                default -> throw new Exception("Unsupported type");
            };
            items.get(name).add(ret);
        } else {
            ret = items.get(name).getFirst().copy();
        }
        return ret;
    }
}
