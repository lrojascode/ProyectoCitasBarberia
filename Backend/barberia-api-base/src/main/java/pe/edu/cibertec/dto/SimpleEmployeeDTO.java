package pe.edu.cibertec.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SimpleEmployeeDTO {
    private Long id;
    private String first_name;
    private String last_name;
    private String profession;
    private String picture;
}
