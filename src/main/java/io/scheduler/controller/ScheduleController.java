package io.scheduler.controller;

import io.scheduler.model.request.NotificationScheduleRequest;
import io.scheduler.model.response.NotificationScheduleResponse;
import io.scheduler.service.impl.SchedulerServiceImpl;
import org.quartz.JobDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.OK;

@RestController
public class ScheduleController {


    private SchedulerServiceImpl schedulerService;

    @Autowired
    public ScheduleController(SchedulerServiceImpl schedulerService) {
        this.schedulerService = schedulerService;
    }

    @PostMapping("/api/schedule")
    public ResponseEntity<NotificationScheduleResponse> scheduleNotification(@Valid @RequestBody NotificationScheduleRequest request){
        JobDetail jobDetail = this.schedulerService.schedule(request);

        NotificationScheduleResponse response = new NotificationScheduleResponse(true,
                jobDetail.getKey().getName(),
                jobDetail.getKey().getGroup(),
                "Job Scheduled");

        return new ResponseEntity<>(response, OK);
    }
}
