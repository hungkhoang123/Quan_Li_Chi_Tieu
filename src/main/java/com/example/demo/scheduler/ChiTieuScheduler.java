package com.example.demo.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class ChiTieuScheduler {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    // Cứ 10 giây chạy 1 lần
    @Scheduled(fixedRate = 10000)
    public void hienThiThongBao() {
        System.out.println("Task đang chạy lúc: " + LocalDateTime.now().format(formatter));
    }
}
