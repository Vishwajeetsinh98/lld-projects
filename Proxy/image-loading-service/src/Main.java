import imageservice.ImageService;
import imageservice.proxy.ImageServiceProxy;
import imageservice.util.Image;

public class Main {
    public static void main(String[] args) {
        ImageService imageServiceProxy = new ImageServiceProxy();

        imageServiceProxy.addImage("test", new Image("test1", "/var/tmp/test1.jpg"));
        System.out.println();
        imageServiceProxy.addImage("test1", new Image("test2", "/var/tmp/test2.jpg"));
        System.out.println();
        imageServiceProxy.addImage("test2", new Image("test3", "/var/tmp/test3.jpg"));
        System.out.println();
        imageServiceProxy.addImage("test3", new Image("test4", "/var/tmp/test4.jpg"));
        System.out.println();
        imageServiceProxy.addImage("test4", new Image("test5", "/var/tmp/test5.jpg"));
        System.out.println();

        System.out.println("Getting images list from service");
        for (String imgName : imageServiceProxy.getImages())
            System.out.println(imgName);
        System.out.println();
        imageServiceProxy.getImage("test");
        System.out.println();
        imageServiceProxy.getImage("test");
    }
}