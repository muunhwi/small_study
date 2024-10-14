package com.smallstudy.dto;

import com.smallstudy.domain.value_type.Region;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegionDTO {

    Long code;
    String text;

    public RegionDTO(Long id, Region region) {
        code = id;
        text =  String.format("%s %s %s", region.getCity(), region.getGu(), region.getDong());
    }
}
