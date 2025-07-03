import renderers.impl.RasterRenderer;
import renderers.impl.VectorizedRenderer;
import shapes.Shape;
import shapes.impl.Circle;
import shapes.impl.Square;

public class Main {
    public static void main(String[] args) {
        VectorizedRenderer vectorizedRenderer = new VectorizedRenderer();
        RasterRenderer renderer = new RasterRenderer();
        Shape circle = new Circle(renderer, 1, 1, 10);
        Shape square = new Square(renderer, 10, 10, 5);

        Shape vectorizedCircle = new Circle(vectorizedRenderer, 1, 1, 10);
        Shape vectorizedSquare = new Square(vectorizedRenderer, 10, 10, 5);

        circle.show();
        square.show();
        vectorizedCircle.show();
        vectorizedSquare.show();
    }
}