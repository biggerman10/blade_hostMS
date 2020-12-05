/*
 *      Copyright (c) 2018-2028, Chill Zhuang All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *
 *  Redistributions of source code must retain the above copyright notice,
 *  this list of conditions and the following disclaimer.
 *  Redistributions in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in the
 *  documentation and/or other materials provided with the distribution.
 *  Neither the name of the dreamlu.net developer nor the names of its
 *  contributors may be used to endorse or promote products derived from
 *  this software without specific prior written permission.
 *  Author: Chill 庄骞 (smallchill@163.com)
 */
package org.springblade.modules.host.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import lombok.AllArgsConstructor;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springblade.core.excel.util.ExcelUtil;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springblade.modules.host.excel.HostExcel;
import org.springblade.modules.host.excel.HostImporter;
import org.springblade.modules.newhost.excel.NewHostImporter;
import org.springblade.modules.newhost.excel.NewhostExcel;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.modules.host.entity.Host;
import org.springblade.modules.host.vo.HostVO;
import org.springblade.modules.host.service.IHostService;
import org.springblade.core.boot.ctrl.BladeController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 *  控制器
 *
 * @author BladeX
 * @since 2020-07-02
 */
@RestController
@AllArgsConstructor
@RequestMapping("blade-host/host")
@Api(value = "", tags = "接口")
public class HostController extends BladeController {

	private final IHostService hostService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入host")
	public R<Host> detail(Host host) {
		Host detail = hostService.getOne(Condition.getQueryWrapper(host));
		return R.data(detail);
	}

	/**
	 * 分页
	 */

	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入host")
	public R<IPage<Host>> list(Host host, Query query) {
		IPage<Host> pages = hostService.page(Condition.getPage(query), Condition.getQueryWrapper(host));
		return R.data(pages);
	}

	/**
	 * 自定义分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "分页", notes = "传入host")
	public R<IPage<HostVO>> page(HostVO host, Query query) {
		IPage<HostVO> pages = hostService.selectHostPage(Condition.getPage(query), host);
		return R.data(pages);
	}

	/**
	 * 新增
	 */
	@PostMapping("/save")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入host")
	public R save(@Valid @RequestBody Host host) {
		return R.status(hostService.save(host));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入host")
	public R update(@Valid @RequestBody Host host) {
		return R.status(hostService.updateById(host));
	}

	/**
	 * 新增或修改
	 */
	@PostMapping("/submit")
	@ApiOperationSupport(order = 6)
	@ApiOperation(value = "新增或修改", notes = "传入host")
	public R submit(@Valid @RequestBody Host host) {
		return R.status(hostService.saveOrUpdate(host));
	}


	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(hostService.deleteLogic(Func.toLongList(ids)));
	}



	@PostMapping("import-host")
	public R importHost(MultipartFile file, Integer isCovered){
		HostImporter hostImporter=new HostImporter(hostService,isCovered==1);
		ExcelUtil.save(file, hostImporter, HostExcel.class);
		return R.success("操作成功");
	}

	@GetMapping("export-template")

	public void exportUser(HttpServletResponse response) {
		List<HostExcel> list = new ArrayList<>();
//		ArrayList<Integer> lis
		ExcelUtil.export(response, "主播数据模板", "用户数据表", list, HostExcel.class);
	}


}
