package org.jeecg.modules.demo.aa.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.demo.aa.entity.RiskReport;
import org.jeecg.modules.demo.aa.service.IRiskReportService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.apache.shiro.authz.annotation.RequiresPermissions;

 /**
 * @Description: 风险报送
 * @Author: jeecg-boot
 * @Date:   2023-07-19
 * @Version: V1.0
 */
@Api(tags="风险报送")
@RestController
@RequestMapping("/aa/riskReport")
@Slf4j
public class RiskReportController extends JeecgController<RiskReport, IRiskReportService> {
	@Autowired
	private IRiskReportService riskReportService;
	
	/**
	 * 分页列表查询
	 *
	 * @param riskReport
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "风险报送-分页列表查询")
	@ApiOperation(value="风险报送-分页列表查询", notes="风险报送-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<RiskReport>> queryPageList(RiskReport riskReport,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<RiskReport> queryWrapper = QueryGenerator.initQueryWrapper(riskReport, req.getParameterMap());
		Page<RiskReport> page = new Page<RiskReport>(pageNo, pageSize);
		IPage<RiskReport> pageList = riskReportService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param riskReport
	 * @return
	 */
	@AutoLog(value = "风险报送-添加")
	@ApiOperation(value="风险报送-添加", notes="风险报送-添加")
	@RequiresPermissions("aa:risk_report:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody RiskReport riskReport) {
		riskReportService.save(riskReport);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param riskReport
	 * @return
	 */
	@AutoLog(value = "风险报送-编辑")
	@ApiOperation(value="风险报送-编辑", notes="风险报送-编辑")
	@RequiresPermissions("aa:risk_report:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody RiskReport riskReport) {
		riskReportService.updateById(riskReport);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "风险报送-通过id删除")
	@ApiOperation(value="风险报送-通过id删除", notes="风险报送-通过id删除")
	@RequiresPermissions("aa:risk_report:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		riskReportService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "风险报送-批量删除")
	@ApiOperation(value="风险报送-批量删除", notes="风险报送-批量删除")
	@RequiresPermissions("aa:risk_report:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.riskReportService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "风险报送-通过id查询")
	@ApiOperation(value="风险报送-通过id查询", notes="风险报送-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<RiskReport> queryById(@RequestParam(name="id",required=true) String id) {
		RiskReport riskReport = riskReportService.getById(id);
		if(riskReport==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(riskReport);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param riskReport
    */
    @RequiresPermissions("aa:risk_report:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, RiskReport riskReport) {
        return super.exportXls(request, riskReport, RiskReport.class, "风险报送");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequiresPermissions("aa:risk_report:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, RiskReport.class);
    }

}
