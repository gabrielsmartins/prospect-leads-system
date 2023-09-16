package br.pucminas.bff.adapters.web.in.products.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder(setterPrefix = "with")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PageDto<T> {

    @JsonProperty("data")
    private List<T> data;

    @JsonProperty("page")
    private Integer page;

    @JsonProperty("page_size")
    private Integer pageSize;

    @JsonProperty("total_pages")
    private Integer totalPages;

    @JsonProperty("total_elements")
    private Long totalElements;

    @JsonProperty("first")
    private boolean first;

    @JsonProperty("last")
    private boolean last;

}
