package utez.edu.mx.crochetifyBack.dto;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseList {
    private boolean success;
    private String message;
    private Map<String, List<?>> response;
}
