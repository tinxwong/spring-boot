package com.tinx.java.permission.entity.vo;

import lombok.Data;

import java.util.List;

/**
 * @author tinx
 * @date 2018-11-8 17:07
 */
@Data
public class FirstLevel {

    private Long id ;

    private List<SecondLevel> menu;
}
