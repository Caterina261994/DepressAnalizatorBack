package depress_analizator.service.color;

import org.apache.commons.io.FileUtils;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class FaceRecognition{
    public boolean isDepress (InputStream file) throws URISyntaxException, IOException {

        System.loadLibrary( Core.NATIVE_LIBRARY_NAME );

        File file1 = Paths.get("depress-analyze-back","src","main","resources","1.jpg").toFile();
        BufferedWriter writer = Files.newBufferedWriter(Paths.get("depress-analyze-back","src","main","resources","1.jpg"));
        writer.write("");
        writer.flush();
        FileUtils.copyInputStreamToFile(file, file1);
        Mat src = Imgcodecs.imread(file1.getPath());

        File file11 = Paths.get("depress-analyze-back","src","main","resources","haarcascade_frontalface_alt2.xml").toFile();
        String xmlFile = file11.getAbsolutePath();
        CascadeClassifier classifier = new CascadeClassifier(xmlFile);

        MatOfRect faceDetections = new MatOfRect();
        classifier.detectMultiScale(src, faceDetections);

        for (Rect rect : faceDetections.toArray()) {
            Imgproc.rectangle(
                    src,
                    new Point(rect.x, rect.y),
                    new Point(rect.x + rect.width, rect.y + rect.height),
                    new Scalar(0, 0, 255),
                    3
            );
        }

        BufferedWriter writer2 = Files.newBufferedWriter(Paths.get("depress-analyze-back","src","main","resources","2.jpg"));
        writer.write("");
        writer.flush();
        Imgcodecs.imwrite(Paths.get("depress-analyze-back","src","main","resources","2.jpg").toString(), src);
        return faceDetections.toArray().length < 1;
    }
}