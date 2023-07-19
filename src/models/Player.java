package models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Player {
    private Long id;
    private String name;
    private Integer time;
    private Integer level;
    private String type;

}
