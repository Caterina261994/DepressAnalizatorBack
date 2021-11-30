package depress_analizator.service.color;

import depress_analizator.model.Pixel;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class HslProcessor {
    private static final int MAX_RGB_VAL = 256;
    static int warmCount = 0;
    static int coolCount = 0;
    static int neitralCount = 0;
    public List<Pixel> pixelList= new ArrayList<Pixel>();

    public boolean hslProcess(InputStream file) throws IOException {

        BufferedImage source = ImageIO.read(file);
        toHSL(source);
        return isDepress(pixelList);

    }
    private void toHSL(BufferedImage source){
        Pixel pixel;
        for (int x = 0; x < source.getWidth(); x++) {
            for (int y = 0; y < source.getHeight(); y++) {
                pixel = new Pixel();

                Color color = new Color(source.getRGB(x, y));

                double blue = (double) color.getBlue() / MAX_RGB_VAL;
                double red = (double) color.getRed() / MAX_RGB_VAL;
                double green = (double)color.getGreen() / MAX_RGB_VAL;
                double max = Math.max(red,Math.max(blue,green));
                double min = Math.min(red,Math.min(blue,green));
                double a = (green - blue) / (max-min);

                pixel.setL(1/2*(max+min));
                if(max == min){
                    pixel.setH(0);
                }else if( max== red && green >= blue){
                    pixel.setH(60 * a);
                }else if( max == red && green < blue) {
                    pixel.setH(60 * a + 360);
                }else if (max == green){
                    pixel.setH(60 * ((blue - red) / (max - min)) +120);
                }else if (max == blue){
                    pixel.setH(60 * ((red-green)/(max - min)) + 240);
                }
                if(max==0){
                    pixel.setS(0);
                }else if(pixel.getL() <= 1/2 && pixel.getL() > 0){
                    pixel.setS((max-min)/(max+min));
                }else{
                    pixel.setS((max-min)/((double)2-(max+min)));}
                pixelList.add(pixel);
            }
        }
    }
    private boolean isDepress(List<Pixel> pixelList){
        for(Pixel pixel : pixelList) {
            if(pixel.getL()>0.9 || pixel.getS()< 0.2){
                coolCount++;
            }else warmCount++;
        }
        return coolCount > warmCount;
    }
}