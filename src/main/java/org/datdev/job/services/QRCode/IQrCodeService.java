package org.datdev.job.services.QRCode;

import com.google.zxing.WriterException;
import java.io.IOException;

public interface IQrCodeService {
    String generateQRCodeUrl(String content, String fileName) throws IOException, WriterException;
    void deleteQrCodeImage(String fileName) throws IOException;

    // Tạo mã QR từ một URL
    String generateQRCodeUrlFromURL(String url, String fileName) throws IOException, WriterException;

    // Tạo mã QR với kích thước tùy chỉnh
    String generateCustomQRCodeUrl(String content, String fileName, int width, int height) throws IOException, WriterException;

    // Tạo mã QR với thông tin định dạng
    String generateFormattedQRCodeUrl(String content, String fileName, String format) throws IOException, WriterException;
}
