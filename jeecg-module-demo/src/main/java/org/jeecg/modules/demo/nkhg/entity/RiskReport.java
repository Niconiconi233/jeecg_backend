package org.jeecg.modules.demo.nkhg.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: 风险报送
 * @Author: jeecg-boot
 * @Date:   2023-06-14
 * @Version: V1.0
 */
@Data
@TableName("risk_report")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="risk_report对象", description="风险报送")
public class RiskReport implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private java.lang.String id;
	/**创建人*/
    @ApiModelProperty(value = "创建人")
    private java.lang.String createBy;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建日期")
    private java.util.Date createTime;
	/**更新人*/
    @ApiModelProperty(value = "更新人")
    private java.lang.String updateBy;
	/**更新日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新日期")
    private java.util.Date updateTime;
	/**所属部门*/
    @ApiModelProperty(value = "所属部门")
    private java.lang.String sysOrgCode;
	/**是否自举*/
	@Excel(name = "是否自举", width = 15)
    @ApiModelProperty(value = "是否自举")
    private java.lang.Integer isSelf;
	/**被举报人*/
	@Excel(name = "被举报人", width = 15)
    @ApiModelProperty(value = "被举报人")
    private java.lang.String reportedPersion;
	/**风险情况*/
	@Excel(name = "风险情况", width = 15)
    @ApiModelProperty(value = "风险情况")
    private java.lang.String riskInfo;
	/**风险数量*/
	@Excel(name = "风险数量", width = 15)
    @ApiModelProperty(value = "风险数量")
    private java.lang.Double riskAccount;
	/**是否处置*/
	@Excel(name = "是否处置", width = 15)
    @ApiModelProperty(value = "是否处置")
    private java.lang.Integer isDispose;
	/**处置情况*/
	@Excel(name = "处置情况", width = 15)
    @ApiModelProperty(value = "处置情况")
    private java.lang.String disposeSituation;
	/**是否问责*/
	@Excel(name = "是否问责", width = 15)
    @ApiModelProperty(value = "是否问责")
    private java.lang.Integer isAccountability;
	/**问责情况*/
	@Excel(name = "问责情况", width = 15)
    @ApiModelProperty(value = "问责情况")
    private java.lang.String accountabilitySituation;
	/**问责文件*/
	@Excel(name = "问责文件", width = 15)
    @ApiModelProperty(value = "问责文件")
    private java.lang.String accountabilityFile;
}
