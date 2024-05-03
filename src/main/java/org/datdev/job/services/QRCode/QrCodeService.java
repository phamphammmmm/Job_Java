package org.datdev.job.services.QRCode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
import java.util.Objects;

@Service
public class QrCodeService implements IQrCodeService {
    @Autowired
    private Environment env;

    @Override
    public String generateQRCodeUrl(String content, String fileName) throws IOException, WriterException {
        return generateCustomQRCodeUrl(content, fileName, 200, 200);
    }

    @Override
    public void deleteQrCodeImage(String fileName) throws IOException {
        String directoryPath = getQrCodeDirectoryPath();
        Path qrCodePath = Paths.get(directoryPath, fileName + ".png");

        if (Files.exists(qrCodePath)) {
            Files.delete(qrCodePath);
        }
    }

    @Override
    public String generateQRCodeUrlFromURL(String url, String fileName) throws IOException, WriterException {
        return generateQRCodeUrl(url, fileName);
    }

    @Override
    public String generateCustomQRCodeUrl(String content, String fileName, int width, int height) throws IOException, WriterException {
        String directoryPath = getQrCodeDirectoryPath();

        var path = Paths.get(directoryPath);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, width, height);
        Path qrCodePath = Paths.get(directoryPath, fileName + ".png");
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", qrCodePath);

        String url = getQrCodeUrl(fileName);
        System.out.println("Generated QR Code URL: " + url);

        return url;
    }

    @Override
    public String generateFormattedQRCodeUrl(String content, String fileName, String format) throws IOException, WriterException {
        // Chưa có triển khai cho phương thức này
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    private String getQrCodeDirectoryPath() {
        return Paths.get(Objects.requireNonNull(env.getProperty("user.dir")), "src", "main", "resources", "static", "qrcodes").toString();
    }

    private String getQrCodeUrl(String fileName) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        return ServletUriComponentsBuilder.fromContextPath(attributes.getRequest()).path("/qrcodes/" + fileName + ".png").build().toUriString();
    }
}
