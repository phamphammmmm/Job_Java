package org.datdev.job.services.QRCode;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import org.datdev.job.entities.User;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.util.FileSystemUtils;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;

@Service
public class QrCodeService implements IQrCodeService {
    @Autowired
    private Environment env;

    public String generateQRCodeUrl(User user) throws IOException, WriterException {
        String webRootPath = env.getProperty("server.servlet.context-path");
        String directoryPath = Paths.get(env.getProperty("user.dir"), "src", "main", "resources", "static", "qrcodes").toString();

        if (!Files.exists(Paths.get(directoryPath))) {
            Files.createDirectories(Paths.get(directoryPath));
        }

        String userDataJson = new ObjectMapper().writeValueAsString(user);

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(userDataJson, BarcodeFormat.QR_CODE, 200, 200);
        Path qrCodePath = Paths.get(directoryPath, user.getId() + "_qrcode.png");
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", qrCodePath);

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String url = ServletUriComponentsBuilder.fromContextPath(attributes.getRequest()).path("/qrcodes/" + user.getId() + "_qrcode.png").build().toUriString();
        System.out.println("Generated QR Code URL: " + url);

        return url;
    }

    @Override
    public void deleteQrCodeImage(int id) throws IOException {
        String directoryPath = Paths.get(env.getProperty("user.dir"), "src", "main", "resources", "static", "qrcodes").toString();
        Path qrCodePath = Paths.get(directoryPath, id + "_qrcode.png");

        if (Files.exists(qrCodePath)) {
            Files.delete(qrCodePath);
        }
    }
}
