package pe.edu.cibertec.dto;

import java.time.LocalDateTime;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDTO {
    private Long id;
    private SimpleEmployeeDTO employee;
    private SimpleServiceDTO service;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime datetime;
    @JsonFormat(pattern="HH:mm:ss")
    private LocalTime endTime;
    private Boolean cancelled;
}
