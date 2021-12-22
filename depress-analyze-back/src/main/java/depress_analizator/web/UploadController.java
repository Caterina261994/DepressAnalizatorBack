package depress_analizator.web;

import depress_analizator.model.dto.DepressResultEnd;
import depress_analizator.service.color.DepresAnalizator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URISyntaxException;

@CrossOrigin("*")
@RestController
public class UploadController {
    @Autowired
    DepresAnalizator depresAnalizator;

    @PostMapping(value = "/uploadImage")
    public ResponseEntity uploadImage(@RequestParam("file")MultipartFile[] files) throws IOException, URISyntaxException {


        DepressResultEnd depressanalizator = depresAnalizator.depressanalizator(files);
        return new ResponseEntity(depressanalizator, HttpStatus.OK);
    }
}
