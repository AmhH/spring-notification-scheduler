package io.scheduler.service;

import io.scheduler.model.SchedulerJobInfo;

public interface SchedulerService {
    void scheduleNewJob(SchedulerJobInfo jobInfo);

    void updateScheduleJob(SchedulerJobInfo jobInfo);

    boolean unScheduleJob(String jobName);

    boolean deleteJob(SchedulerJobInfo jobInfo);
}
