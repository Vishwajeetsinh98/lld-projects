package imageservice;

import imageservice.util.Image;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RealImageService implements ImageService {

    private final Map<String, Image> images;

    public RealImageService() {
        System.out.println("RealImageService instantiated");
        try {
            Thread.sleep(1000 * 5);
        } catch (InterruptedException e) {
            // do nothing
        }
        images = new HashMap<>();
    }

    @Override
    public List<String> getImages() {
        System.out.println("RealImageService: getImages");
        try {
            Thread.sleep(1000 * 5);
        } catch (InterruptedException e) {
            // do nothing
        }
        List<String> ret = new ArrayList<>();
        for (String imgCode : images.keySet()) {
            ret.add(images.get(imgCode).getName());
        }
        return ret;
    }

    @Override
    public void addImage(String imgCode, Image image) {
        System.out.println("RealImageService: addImage");
        try {
            Thread.sleep(1000 * 5);
        } catch (InterruptedException e) {
            // do nothing
        }
        this.images.put(imgCode, image);
    }

    @Override
    public Image getImage(String imgCode) {
        System.out.println("RealImageService: getImage");
        try {
            Thread.sleep(1000 * 5);
        } catch (InterruptedException e) {
            // do nothing
        }
        return images.getOrDefault(imgCode, null);
    }
}
