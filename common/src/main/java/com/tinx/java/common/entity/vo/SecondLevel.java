package com.tinx.java.common.entity.vo;

import lombok.Data;

import java.util.List;

/**
 * @author tinx
 * @date 2018-11-8 17:07
 */
@Data
public class SecondLevel {

    private String text;

    private List<ThridLevel> items;
}
