package renderers.impl;

import renderers.Renderer;

public class RasterRenderer implements Renderer {
    @Override
    public void drawSquare(int posX, int posY, int side) {
        // Print blank lines for vertical offset
        for (int i = 0; i < posY; i++) {
            System.out.println();
        }

        // Print square rows
        for (int i = 0; i < side; i++) {

            // Print spaces for horizontal offset
            for (int j = 0; j < posX; j++) {
                System.out.print(" ");
            }

            // Print square
            for (int k = 0; k < side; k++) {
                System.out.print("* ");
            }

            System.out.println();
        }
    }

    @Override
    public void drawCircle(int posX, int posY, int radius) {
        // Print blank lines for vertical offset
        for (int i = 0; i < posY; i++) {
            System.out.println();
        }

        double threshold = 0.5;  // Controls circle "thickness"

        for (int y = -radius; y <= radius; y++) {

            // Horizontal offset before each row
            for (int k = 0; k < posX; k++) {
                System.out.print(" ");
            }

            for (int x = -radius; x <= radius; x++) {
                double distance = Math.sqrt(x * x + y * y);

                if (Math.abs(distance - radius) < threshold) {
                    System.out.print("* ");
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
    }
}
