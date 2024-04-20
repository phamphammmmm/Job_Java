package org.datdev.job.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Env {
    @Value("${server.port}")
    public static String PORT;
}
