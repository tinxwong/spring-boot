package com.tinx.java.chipin.entity.vo;

import com.tinx.java.chipin.enums.TaskStatusEnum;
import com.tinx.java.common.utils.DateUtil;
import lombok.Data;

import java.util.Date;

/**
 * @author tinx
 * @date 2018-10-9 21:24
 */
@Data
public class ChipinVo {
    /**
     * 唯一标识
     */
    private Long id;
    /**
     * 创建人
     */
    private String createUser;
    /**
     * 修改人
     */
    private String updateUser;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;

    public String getCreateTimeString(){
        return DateUtil.format(getCreateTime(),"yyyy-MM-dd HH:mm:ss");
    }

    public String getUpdateTimeString(){
        return DateUtil.format(getUpdateTime(),"yyyy-MM-dd HH:mm:ss");
    }
}
