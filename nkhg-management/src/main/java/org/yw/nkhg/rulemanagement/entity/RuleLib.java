package org.yw.nkhg.rulemanagement.entity;

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
 * @Description: 规章制度管理模块
 * @Author: jeecg-boot
 * @Date:   2023-08-28
 * @Version: V1.0
 */
@Data
@TableName("rule_lib")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="rule_lib对象", description="规章制度管理模块")
public class RuleLib implements Serializable {
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
	/**制度标题*/
	@Excel(name = "制度标题", width = 15)
    @ApiModelProperty(value = "制度标题")
    private java.lang.String title;
	/**文号*/
	@Excel(name = "文号", width = 15)
    @ApiModelProperty(value = "文号")
    private java.lang.String docNo;
	/**印发时间*/
	@Excel(name = "印发时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "印发时间")
    private java.util.Date dateOfIssuance;
	/**是否现用*/
	@Excel(name = "是否现用", width = 15)
    @ApiModelProperty(value = "是否现用")
    private java.lang.Integer inUse;
	/**失效时间*/
	@Excel(name = "失效时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "失效时间")
    private java.util.Date failureTime;
	/**文件地址*/
	@Excel(name = "文件地址", width = 15)
    @ApiModelProperty(value = "文件地址")
    private java.lang.String fileUrl;
}
