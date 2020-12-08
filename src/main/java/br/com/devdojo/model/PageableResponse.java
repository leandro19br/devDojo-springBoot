package br.com.devdojo.model;

/*
 *Classe responsável pelo parse para List de studant
 */

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

public class PageableResponse<T> extends PageImpl<T> {

    private boolean last;
    private boolean first;
    private int totalPages;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public PageableResponse(@JsonProperty("content") List<T> content,
                            @JsonProperty("number") int number,
                            @JsonProperty("size") int size,
                            @JsonProperty("totalElements") Long totalElements,
                            @JsonProperty("pageable")JsonNode pageable,
                            @JsonProperty("first") boolean first,
                            @JsonProperty("last") boolean last,
                            @JsonProperty("totalPages") int totalPages,
                            @JsonProperty("sort") JsonNode sort,
                            @JsonProperty("numberOfElements") int numberOfElements){
        super(content, PageRequest.of(number, size, Sort.by("nome").ascending()), totalElements);
    }

    public PageableResponse() {
        super(new ArrayList());
    }


}
