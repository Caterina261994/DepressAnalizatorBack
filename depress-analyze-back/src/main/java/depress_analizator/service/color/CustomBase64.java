package depress_analizator.service.color;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

@Service
public class CustomBase64 {
    public String makeBase64(InputStream inputStream) throws IOException {

        byte[] bytes = IOUtils.toByteArray(inputStream);
        String base64 = Base64.encodeBase64String(bytes);
        return base64;
    }
    public byte[] makeInputStream(String base64){
        byte[] bytes = Base64.decodeBase64(base64);
        //InputStream inputStream = new ByteArrayInputStream(bytes);
        return bytes;
    }
}
