package depress_analizator.service.color;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Paths;
import java.util.Iterator;

@Service
public class Compresssion64 {
    @Autowired
    CustomBase64 customBase64;
    public String compression(InputStream inputStream) throws IOException {

        BufferedImage image = ImageIO.read(inputStream);

        File compressedImageFile = Paths.get("depress-analyze-back","src","main","resources","3.jpg").toFile();
        OutputStream os = new FileOutputStream(compressedImageFile);

        Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("jpg");
        ImageWriter writer = (ImageWriter) writers.next();

        ImageOutputStream ios = ImageIO.createImageOutputStream(os);
        writer.setOutput(ios);

        ImageWriteParam param = writer.getDefaultWriteParam();

        param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        param.setCompressionQuality(0.3f);
        writer.write(null, new IIOImage(image, null, null), param);

        os.close();
        ios.close();
        writer.dispose();
        InputStream inputStream1 = new FileInputStream(compressedImageFile);

        return customBase64.makeBase64(inputStream1);
    }
}

