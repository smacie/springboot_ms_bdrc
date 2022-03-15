package com.bdrecruit.springbootmsbdrc.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class User {

    private String id;
    private String name;
    private String gender;
    private String profession;
    private Boolean active;
}
