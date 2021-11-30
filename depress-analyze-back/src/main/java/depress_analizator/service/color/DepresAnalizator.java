package depress_analizator.service.color;

import depress_analizator.model.dto.DepressResultEnd;
import depress_analizator.model.entity.DepressResult;
import depress_analizator.model.entity.Photo;
import depress_analizator.repository.DepressResultRepository;
import depress_analizator.repository.FotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class DepresAnalizator {
    @Autowired
    private Compresssion64 compresssion64;
    @Autowired
    private DepressResultRepository depressResultRepository;
    @Autowired
    private FotoRepository fotoRepository;
    @Autowired
    private HslProcessor hslProcessor;
    @Autowired
    private RgbProcessor rgbProcessor;
    @Autowired
    private GreyDepressFoto greyDepressFoto;
    @Autowired
    private FaceRecognition faceRecognition;
    public DepressResultEnd depressanalizator(MultipartFile[] files) throws IOException, URISyntaxException {
        List<Photo> fotoDepressResultList = new ArrayList<>();
        List<String> StrinLgist = new ArrayList<>();
        LocalDate localDate = LocalDate.now();
        int depresspercent;
        int facecounter = 0;
        int depresscounter = 0;
        int midleLenght = files.length/2;
              DepressResultEnd depressResultEnd = new DepressResultEnd();
        for (MultipartFile e : files){
            Photo fotoDepressResult = new Photo();
            if(hslProcessor.hslProcess(e.getInputStream()) && rgbProcessor.rgbProcess(e.getInputStream())){
                depresscounter++;
                String str = compresssion64.compression(greyDepressFoto.greyFoto(e.getInputStream()));
                fotoDepressResult.setDepress(true);
                fotoDepressResult.setFoto(str);
                fotoDepressResultList.add(fotoDepressResult);
            }else {
                String str = compresssion64.compression(e.getInputStream());
                fotoDepressResult.setDepress(false);
                fotoDepressResult.setFoto(str);
                fotoDepressResultList.add(fotoDepressResult);
            }
            if(!faceRecognition.isDepress(e.getInputStream())){
                facecounter++;
            }
        }
        depresspercent =(int)((double)depresscounter/(double) files.length *100);
        DepressResult depressResult = new DepressResult();
        depressResult.setCreateDate(localDate);
        depressResult.setDepressPercent(depresspercent);
        DepressResult depressResult1 = depressResultRepository.save(depressResult);
        for (Photo l : fotoDepressResultList){
            l.setDepressResult(depressResult1);
            fotoRepository.save(l);
            StrinLgist.add(l.getFoto());
        }
        List<String> strings = new ArrayList<>();
        for (String s: StrinLgist){
            String temp = "<img src=data:image/jpg;base64," + s + ">";
            strings.add(temp);
        }

        depressResultEnd.setDepress(depresscounter>midleLenght);
        depressResultEnd.setCountdepress(depresspercent);
        depressResultEnd.setS(strings);
        depressResultEnd.setFacePercent((int)((double)facecounter/(double)files.length*100));

        return depressResultEnd;
    }
}
