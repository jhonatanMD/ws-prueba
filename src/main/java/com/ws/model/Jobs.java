package com.ws.model;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Jobs {


    private Long id;

    @NotNull(message = "name es necesario")
    private String name;

    @NotNull(message = "salary es necesario")
    private BigDecimal salary;

}
