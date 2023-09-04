
package com.lsl.user.api.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * <p>
 * 部门管理
 * </p>
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Dept extends Model<Dept> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "dept_id", type = IdType.ASSIGN_ID)
    private Long deptId;

    /**
     * 部门名称
     */
    @NotBlank(message = "部门名称不能为空")
    private String name;

    /**
     * 排序
     */
    @NotNull(message = "排序值不能为空")
    private Integer sortOrder;

    /**
     * 创建人
     */
    @TableField(fill = FieldFill.INSERT)
    private String createBy;

    /**
     * 修改人
     */
    @TableField(fill = FieldFill.UPDATE)
    private String updateBy;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;

    /**
     * 父级部门id
     */
    private Long parentId;

    /**
     * 是否删除 1：已删除 0：正常
     */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private String delFlag;

}
