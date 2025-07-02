import prototype.PrototypeRegistry;
import shapes.Circle;
import shapes.Shape;
import shapes.Triangle;

public class Main {
    public static void main(String[] args) throws Exception {
        PrototypeRegistry registry = new PrototypeRegistry();
        Shape circle = registry.getByName("circle");
        Shape circleNew = registry.getByName("circle");
        Shape blueCircle = registry.getByColor("circle", "BLUE");
        ((Circle)blueCircle).setRadius(10);
        System.out.println(circle);
        System.out.println(circleNew);
        System.out.println(blueCircle);

        Shape rectangle = registry.getByName("rectangle");
        System.out.println(rectangle);

        Shape triangle = registry.getByColor("triangle", "GREEN");
        ((Triangle)triangle).setBase(10);
        ((Triangle)triangle).setHeight(12);
        System.out.println(triangle);
    }
}