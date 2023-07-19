package models;



import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
public class Node implements Serializable {
    private int row;
    private int col;
    private int zone;
    private int value;
    private boolean protect;

    public Node(int row, int col, int value, boolean protect) {
        this.row = row;
        this.col = col;
        this.value = value;
        this.protect = protect;
        this.zone = calculateZone();
    }
    private int calculateZone(){
        return  (row - 1) / 3 * 3 + (col - 1) / 3 + 1;
    }


}
