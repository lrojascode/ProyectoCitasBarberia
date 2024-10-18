package pe.edu.cibertec.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmpleadoDTO {
    private Long id;
    private String first_name;
    private String last_name;
    private String profession;
    private String picture;
    private List<SimpleServiceDTO> services;
    private String workingDays;
    private Boolean active;
}
