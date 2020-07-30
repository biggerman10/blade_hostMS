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
package org.springblade.modules.gift.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.cache.utils.CacheUtil;
import org.springblade.core.excel.util.ExcelUtil;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springblade.modules.gift.entity.HostGift;
import org.springblade.modules.gift.excel.GiftExcel;
import org.springblade.modules.gift.service.IHostGiftService;
import org.springblade.modules.gift.vo.HostGiftVO;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springblade.modules.gift.excel.HostGiftImporter;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 *  控制器
 *
 * @author BladeX
 * @since 2020-07-06
 */
@RestController
@AllArgsConstructor
@RequestMapping("blade-gift/hostgift")
@Api(value = "", tags = "接口")
public class HostGiftController extends BladeController {

	private final IHostGiftService hostGiftService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入hostGift")
	public R<HostGift> detail(HostGift hostGift) {
		HostGift detail = hostGiftService.getOne(Condition.getQueryWrapper(hostGift));
		return R.data(detail);
	}

	/**
	 * 分页
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入hostGift")
	public R<IPage<HostGift>> list(HostGift hostGift, Query query) {
		IPage<HostGift> pages = hostGiftService.page(Condition.getPage(query), Condition.getQueryWrapper(hostGift));
		return R.data(pages);
	}

	/**
	 * 自定义分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "分页", notes = "传入hostGift")
	public R<IPage<HostGiftVO>> page(HostGiftVO hostGift, Query query) {
		if(CacheUtil.get())
		IPage<HostGiftVO> pages = hostGiftService.selectHostGiftPage(Condition.getPage(query), hostGift);
		return R.data(pages);
	}

	/**
	 * 新增
	 */
	@PostMapping("/save")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入hostGift")
	public R save(@Valid @RequestBody HostGift hostGift) {
		return R.status(hostGiftService.save(hostGift));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入hostGift")
	public R update(@Valid @RequestBody HostGift hostGift) {
		return R.status(hostGiftService.updateById(hostGift));
	}

	/**
	 * 新增或修改
	 */
	@PostMapping("/submit")
	@ApiOperationSupport(order = 6)
	@ApiOperation(value = "新增或修改", notes = "传入hostGift")
	public R submit(@Valid @RequestBody HostGift hostGift) {
		return R.status(hostGiftService.saveOrUpdate(hostGift));
	}


	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(hostGiftService.deleteLogic(Func.toLongList(ids)));
	}

	@PostMapping("import-hostGift")
	public R importGift(MultipartFile file, Integer isCovered){
		HostGiftImporter hostGiftImporter=new HostGiftImporter(hostGiftService);
		ExcelUtil.save(file, hostGiftImporter, GiftExcel.class);
		return R.success("操作成功");
	}

	@GetMapping("export-template")
	public void exportGift(HttpServletResponse response) {
		List<GiftExcel> list = new ArrayList<>();
		ExcelUtil.export(response, "主播礼物数据模板", "用户数据表", list,GiftExcel.class);
	}

}
