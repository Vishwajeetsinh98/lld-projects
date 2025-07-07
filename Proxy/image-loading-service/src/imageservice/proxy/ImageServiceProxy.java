package imageservice.proxy;

import imageservice.ImageService;
import imageservice.RealImageService;
import imageservice.util.Image;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ImageServiceProxy implements ImageService {

    private RealImageService realImageService;
    private final Map<String, Image> cache;

    public ImageServiceProxy () {
        System.out.println("ImageServiceProxy instantiated");
        cache = new HashMap<>();
        realImageService = null;
    }

    @Override
    public List<String> getImages() {
        System.out.println("ImageServiceProxy: getImages - Forwarded simply to RealImageService");
        if (realImageService == null)
            realImageService = new RealImageService();
        return realImageService.getImages();
    }

    @Override
    public void addImage(String imgCode, Image image) {
        System.out.println("ImageServiceProxy: addImage");
        if (realImageService == null)
            realImageService = new RealImageService();
        System.out.println("Sending call to RealImageService");
        realImageService.addImage(imgCode, image);
    }

    @Override
    public Image getImage(String imgCode) {
        System.out.println("ImageServiceProxy: getImage");
        if (realImageService == null)
            realImageService = new RealImageService();
        if (cache.containsKey(imgCode))
            return cache.get(imgCode);
        System.out.println("Cache miss - going to RealImageService (SLOW!!)");
        Image ret = realImageService.getImage(imgCode);
        cache.put(imgCode, ret);
        return ret;
    }
}
