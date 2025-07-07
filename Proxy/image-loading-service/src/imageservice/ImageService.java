package imageservice;

import imageservice.util.Image;
import java.util.List;

public interface ImageService {
    public List<String> getImages();
    public void addImage(String imgCode, Image image);
    public Image getImage(String imgCode);
}
