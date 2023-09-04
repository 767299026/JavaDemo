
package com.lsl.user.api.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;

/**
 * <p>
 * 角色菜单表
 * </p>
 *
 * @Data
 * @EqualsAndHashCode(callSuper = true)
 */
public class RoleMenu extends Model<RoleMenu> {

    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 菜单ID
     */
    private Long menuId;

}
