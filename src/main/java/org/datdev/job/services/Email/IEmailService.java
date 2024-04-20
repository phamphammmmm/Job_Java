package org.datdev.job.services.Email;

import jakarta.validation.constraints.Email;

public interface IEmailService {
        void sendEmail(Email request);
    }