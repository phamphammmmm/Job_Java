package org.datdev.job.utils;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CronJobScheduler {

    @Scheduled(cron = "0 0 0 * * ?") // Chạy vào lúc 12 giờ đêm mỗi ngày
    public void scheduleJob() {
        // Thực hiện logic đặt lịch hẹn đăng job ở đây
        // Ví dụ: jobService.createScheduledJob();
        System.out.println("Đã đặt lịch hẹn đăng job");
    }
}
