package com.smallstudy.domain.value_type;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class Region {

    private String city;
    private String gu;
    private String dong;

    public Region() {
    }

    public Region(String city, String gu, String dong) {
        this.city = city;
        this.gu = gu;
        this.dong = dong;
    }
}
