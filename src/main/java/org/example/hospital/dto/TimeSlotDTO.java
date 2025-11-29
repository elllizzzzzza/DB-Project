package org.example.hospital.dto;

import java.time.LocalDateTime;

public class TimeSlotDTO {
    private LocalDateTime start;
    private LocalDateTime end;

    public TimeSlotDTO(LocalDateTime start, LocalDateTime end) {
        this.start = start;
        this.end = end;
    }

    public LocalDateTime getStart() { return start; }
    public LocalDateTime getEnd() { return end; }
    public void setStart(LocalDateTime start) { this.start = start; }
    public void setEnd(LocalDateTime end) { this.end = end; }
}
