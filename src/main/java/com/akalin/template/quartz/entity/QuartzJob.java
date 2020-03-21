package com.akalin.template.quartz.entity;

import com.akalin.template.base.BaseEntity;
import com.akalin.template.quartz.constant.Constant;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @author Exrickx
 */
@Data
@TableName("t_quartz_job")
@ApiModel(value = "定时任务")
public class QuartzJob extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "任务类名")
    private String jobClassName;

    @ApiModelProperty(value = "cron表达式")
    private String cronExpression;

    @ApiModelProperty(value = "参数")
    private String parameter;

    @ApiModelProperty(value = "备注")
    private String description;

    @ApiModelProperty(value = "状态 0正常 -1停止")
    private Integer status = Constant.STATUS_NORMAL;
}
