
package com.lsl.user.api.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 部门关系表
 * </p>
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DeptRelation extends Model<DeptRelation> {

    private static final long serialVersionUID = 1L;

    /**
     * 祖先节点
     */
    private Long ancestor;

    /**
     * 后代节点
     */
    private Long descendant;

}
