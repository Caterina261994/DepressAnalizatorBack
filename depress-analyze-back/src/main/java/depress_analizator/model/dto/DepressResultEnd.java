package depress_analizator.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class DepressResultEnd {
    boolean isDepress;
    int countdepress;
    int FacePercent;
    List<String> s;
}
