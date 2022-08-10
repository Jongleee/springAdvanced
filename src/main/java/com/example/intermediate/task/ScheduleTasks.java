package com.example.intermediate.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ScheduleTasks {
    private static final Logger log = LoggerFactory.getLogger(ScheduleTasks.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

//    @Scheduled(fixedRate = 1000, initialDelay = 1)
//    public void schedule1(){
//        log.info("1초에 한번씩 실행중;");
//    }
//
//    // 실행 시간, 초기 지연시간 지정으로 순서 설정
//    @Scheduled(fixedRate = 5000, initialDelay = 2)
//    public void schedule2(){
//        log.info("5초에 한번씩 실행중");
//        log.info(" 현재시간 : " + dateFormat.format(new Date()));
//    }
//
//    // corn 으로 초(0-59) 분(0-59) 시간(0-23) 일(1-31) 월(1-12) 요일(1-7)
//    @Scheduled(cron="*/10 * * * * *")
//    public void schedule3(){
//        log.info("corn 스케쥴링 : 10초에 한번씩실 * * * *");
//    }

}
