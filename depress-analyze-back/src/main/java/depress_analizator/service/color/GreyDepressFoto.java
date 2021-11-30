package depress_analizator.service.color;

import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

@Service
public class GreyDepressFoto {
    public InputStream greyFoto(InputStream file) throws IOException {
        BufferedImage source = ImageIO.read(file);
        BufferedImage imageGrey = new BufferedImage(source.getWidth(), source.getHeight(), source.getType());
        for (int x = 0; x < source.getWidth(); x++) {
            for (int y = 0; y < source.getHeight(); y++) {

                Color color = new Color(source.getRGB(x, y));

                int blue = color.getBlue();
                int red = color.getRed();
                int green = color.getGreen();

                int grey = (int) (red * 0.299 + green * 0.587 + blue * 0.114);


                int newRed = grey;
                int newGreen = grey;
                int newBlue = grey;

                Color newColor = new Color(newRed, newGreen, newBlue);

                imageGrey.setRGB(x, y, newColor.getRGB());
            }
        }

        File output = Paths.get("depress-analyze-back","src","main","resources","4.jpg").toFile();
        ImageIO.write(imageGrey, "jpg", output);
        InputStream inputStream = new FileInputStream(output);
        return inputStream;
    }
}
