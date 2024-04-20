package org.datdev.job.services;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Objects;

@Service
public class CCCDReaderService {

    @Autowired
    private Environment env;

    public void readDataFromCCCD(MultipartFile file) throws IOException {
        String uploads = Paths.get(env.getProperty("web.root.path"), "upload").toString();
        File uploadDir = new File(uploads);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        String filePath = Paths.get(uploads, Objects.requireNonNull(file.getOriginalFilename())).toString();
        try (InputStream inputStream = file.getInputStream();
             FileOutputStream outputStream = new FileOutputStream(filePath)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }

        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            HttpPost request = new HttpPost(env.getProperty("api.url"));
            String apiKey = env.getProperty("api.key");
            request.setHeader("api-key", apiKey);

            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.addBinaryBody("image", new File(filePath), ContentType.DEFAULT_BINARY, file.getOriginalFilename());
            HttpEntity multipartEntity = builder.build();
            request.setEntity(multipartEntity);

            HttpResponse response = httpClient.execute(request);
            HttpEntity responseEntity = response.getEntity();

            if (responseEntity != null) {
                String responseString = EntityUtils.toString(responseEntity);
                // Parse responseString and handle accordingly
            }
        }
    }
}
