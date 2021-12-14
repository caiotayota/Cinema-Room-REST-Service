package com.caiotayota.cinema.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class Seat {
    
    @NonNull
    private int row;
    @NonNull
    private int column;
    
    private int price;
    
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private boolean isAvailable;
    
}
