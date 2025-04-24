package br.com.jogoemequipe.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Problem {

    private OffsetDateTime timestamp;
    private Integer status;
    private String type;
    private String title;
    private String detail;
    private String message;
    private List<Object> objects;

    @Data
    @Builder
    public static class Object {
        private String name;
        private String userMessage;
    }
}