package depress_analizator.service.color;

import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

@Service
public class RgbProcessor {
    public boolean rgbProcess(InputStream file) throws IOException {
        int warmColor = 0;
        int coolColor = 0;
        int neitralcolor = 0;

        BufferedImage source = ImageIO.read(file);

        for (int x = 0; x < source.getWidth(); x++) {
            for (int y = 0; y < source.getHeight(); y++) {

                Color color = new Color(source.getRGB(x, y));

                int blue = color.getBlue();
                int red = color.getRed();
                int green = color.getGreen();

                if (red > blue) {
                    warmColor++;
                }
                else if (red < blue) {
                    coolColor++;
                }
                else {
                    neitralcolor++;
                }
            }
        }

        int square = source.getHeight()* source.getWidth();
        float commonWarm = (float)warmColor/(float)square * 100;
        float commonCool = (float)coolColor/(float)square * 100;
        float commonNeitral = (float)neitralcolor/(float)square * 100;


        return coolColor > warmColor;
    }
}
