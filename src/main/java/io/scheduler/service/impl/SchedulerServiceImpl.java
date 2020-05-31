package io.scheduler.service.impl;

import io.scheduler.job.NotifierJob;
import io.scheduler.model.request.NotificationScheduleRequest;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.ZonedDateTime;

@Slf4j
@Service
public class SchedulerServiceImpl {

    private Scheduler scheduler;

    @Autowired
    public SchedulerServiceImpl(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public JobDetail schedule(NotificationScheduleRequest request) {
        JobDetail jobDetail = buildJobDetail(request);
        Trigger trigger = buildJobTrigger(jobDetail, ZonedDateTime.of(request.getDateTime(), request.getTimeZone()));
        try {
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return jobDetail;
    }

    private Trigger buildJobTrigger(JobDetail jobDetail, ZonedDateTime startTime) {
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity(new TriggerKey(jobDetail.getJobDataMap().getString("shipmentId") + "T-4"))
                .startAt(Date.from(startTime.toInstant()))
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withMisfireHandlingInstructionFireNow())
                .build();
    }


    private JobDetail buildJobDetail(NotificationScheduleRequest request) {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("shipmentId", request.getShipmentId());
        jobDataMap.put("userId", request.getUserId());
        jobDataMap.put("origin", request.getOrigin());
        jobDataMap.put("destination", request.getDestination());

        return JobBuilder.newJob()
                .ofType(NotifierJob.class)
                .withIdentity(new JobKey(request.getShipmentId() + "T-4"))
                .usingJobData(jobDataMap)
                .storeDurably()
                .build();
    }
}
