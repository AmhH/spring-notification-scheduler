package io.scheduler.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationScheduleResponse {
    private boolean success;
    private String jobId;
    private String jobGroup;
    private String message;
}