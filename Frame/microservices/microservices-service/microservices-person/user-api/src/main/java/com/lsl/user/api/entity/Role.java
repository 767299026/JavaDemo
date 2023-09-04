
package com.lsl.user.api.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * <p>
 * 角色表
 * </p>
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Role extends Model<Role> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "role_id", type = IdType.ASSIGN_ID)
    private Long roleId;

    @NotBlank(message = "角色名称不能为空")
    private String roleName;

    @NotBlank(message = "角色标识不能为空")
    private String roleCode;

    private String roleDesc;

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
     * 删除标识（0-正常,1-删除）
     */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private String delFlag;

}
