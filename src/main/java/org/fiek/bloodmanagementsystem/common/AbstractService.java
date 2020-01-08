package org.fiek.bloodmanagementsystem.common;

import org.springframework.beans.factory.annotation.Value;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLConnection;
import java.util.Base64;
import java.util.UUID;

public abstract class AbstractService {

    @Value("${image.path}")
    String pathImg;

    protected String writeImage(String imageBase64, String fileLocation) throws IOException {
        String name = null;
        if (imageBase64 != null) {
            String base64Image = imageBase64;
            byte[] imageByteArray = Base64.getDecoder().decode(base64Image);
            String contentType = URLConnection.guessContentTypeFromStream(new ByteArrayInputStream(imageByteArray));
            String extension;
            if (contentType != null) {
                extension = contentType.split("/")[1];
            } else
                extension = null;
            if (extension != null) {
                name = UUID.randomUUID().toString();
                FileOutputStream imageOutFile = new FileOutputStream(pathImg + fileLocation + "/" + name + "." + extension);
                imageOutFile.write(imageByteArray);
                imageOutFile.close();
                name += "." + extension;

                }
        }

        return name;
    }
}
