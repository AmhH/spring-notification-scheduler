package io.scheduler.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationScheduleRequest {

    @NotEmpty
    private String shipmentId;

    @NotEmpty
    private String userId;

    @NotEmpty
    private String origin;

    @NotEmpty
    private String destination;

    @NotNull
    private LocalDateTime dateTime;

    @NotNull
    private ZoneId timeZone;
}
