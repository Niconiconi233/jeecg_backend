package org.yw.nkhg.rulemanagement.controller;

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
import org.yw.nkhg.rulemanagement.entity.RuleLib;
import org.yw.nkhg.rulemanagement.service.IRuleLibService;

/**
 * @Description: 规章制度管理模块
 * @Author: jeecg-boot
 * @Date:   2023-08-28
 * @Version: V1.0
 */
@Api(tags="规章制度管理模块")
@RestController
@RequestMapping("/rulemanage/ruleLib")
@Slf4j
public class RuleLibController extends JeecgController<RuleLib, IRuleLibService> {
	@Autowired
	private IRuleLibService ruleLibService;
	
	/**
	 * 分页列表查询
	 *
	 * @param ruleLib
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "规章制度管理模块-分页列表查询")
	@ApiOperation(value="规章制度管理模块-分页列表查询", notes="规章制度管理模块-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<RuleLib>> queryPageList(RuleLib ruleLib,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<RuleLib> queryWrapper = QueryGenerator.initQueryWrapper(ruleLib, req.getParameterMap());
		Page<RuleLib> page = new Page<RuleLib>(pageNo, pageSize);
		IPage<RuleLib> pageList = ruleLibService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param ruleLib
	 * @return
	 */
	@AutoLog(value = "规章制度管理模块-添加")
	@ApiOperation(value="规章制度管理模块-添加", notes="规章制度管理模块-添加")
	@RequiresPermissions("rulemanage:rule_lib:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody RuleLib ruleLib) {
		//添加文件名
		if(ruleLib.getFileName() == null)
		{
			if(ruleLib.getFileUrl() != null)
			{
				String fileurl = ruleLib.getFileUrl();
				String filename = fileurl.substring(fileurl.lastIndexOf("/")+1);
				ruleLib.setFileName(filename);
			}
		}
		ruleLibService.save(ruleLib);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param ruleLib
	 * @return
	 */
	@AutoLog(value = "规章制度管理模块-编辑")
	@ApiOperation(value="规章制度管理模块-编辑", notes="规章制度管理模块-编辑")
	@RequiresPermissions("rulemanage:rule_lib:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody RuleLib ruleLib) {
		ruleLibService.updateById(ruleLib);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "规章制度管理模块-通过id删除")
	@ApiOperation(value="规章制度管理模块-通过id删除", notes="规章制度管理模块-通过id删除")
	@RequiresPermissions("rulemanage:rule_lib:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		ruleLibService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "规章制度管理模块-批量删除")
	@ApiOperation(value="规章制度管理模块-批量删除", notes="规章制度管理模块-批量删除")
	@RequiresPermissions("rulemanage:rule_lib:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.ruleLibService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "规章制度管理模块-通过id查询")
	@ApiOperation(value="规章制度管理模块-通过id查询", notes="规章制度管理模块-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<RuleLib> queryById(@RequestParam(name="id",required=true) String id) {
		RuleLib ruleLib = ruleLibService.getById(id);
		if(ruleLib==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(ruleLib);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param ruleLib
    */
    @RequiresPermissions("rulemanage:rule_lib:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, RuleLib ruleLib) {
        return super.exportXls(request, ruleLib, RuleLib.class, "规章制度管理模块");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequiresPermissions("rulemanage:rule_lib:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, RuleLib.class);
    }




	//暂时不设置下载权限
	//@RequiresPermissions("rulemanage:rule_lib:XXXX")
	@GetMapping(value = "/getFileUrl")
	public Result<String> getFileUrl(@RequestParam(name="fileName") String fileName, @RequestParam(name="bizPath") String bizPath) {
		String tempUrl = ruleLibService.getFileUrl(fileName, bizPath);
		if (tempUrl != null)
		{
			return Result.OK(tempUrl);
		}
		return Result.error("获取外链错误");
	}


}
