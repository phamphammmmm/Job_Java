package org.datdev.job.services.QRCode;

import org.datdev.job.entities.User;

public interface IQrCodeService {
    String generateQRCodeUrl(User user);
    void deleteQrCodeImage(int id);
}
