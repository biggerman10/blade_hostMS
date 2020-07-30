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
package org.springblade.modules.newhost.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
import org.springblade.core.tool.utils.DateUtil;
import org.springblade.core.tool.utils.Func;
import org.springblade.modules.newhost.excel.NewHostImporter;
import org.springblade.modules.newhost.excel.NewhostExcel;
import org.springblade.modules.system.entity.Region;
import org.springblade.modules.system.excel.RegionExcel;
import org.springblade.modules.system.excel.UserExcel;
import org.springblade.modules.system.service.impl.UserServiceImpl;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.modules.newhost.entity.Newhost;
import org.springblade.modules.newhost.vo.NewhostVO;
import org.springblade.modules.newhost.service.INewhostService;
import org.springblade.core.boot.ctrl.BladeController;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

/**
 *  控制器
 *
 * @author BladeX
 * @since 2020-06-27
 */
@RestController
@AllArgsConstructor
@RequestMapping("blade-newhost/newhost")
@Api(value = "", tags = "接口")
public class NewhostController extends BladeController {

	private final INewhostService newhostService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入newhost")
	public R<Newhost> detail(Newhost newhost) {
		Newhost detail = newhostService.getOne(Condition.getQueryWrapper(newhost));
		return R.data(detail);
	}

	/**
	 * 分页
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入newhost")
	public R<IPage<Newhost>> list(Newhost newhost, Query query) {
		IPage<Newhost> pages = newhostService.page(Condition.getPage(query), Condition.getQueryWrapper(newhost));
		return R.data(pages);
	}

	/**
	 * 自定义分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "分页", notes = "传入newhost")
	public R<IPage<NewhostVO>> page(NewhostVO newhost, Query query) {
		IPage<NewhostVO> pages = newhostService.selectNewhostPage(Condition.getPage(query), newhost);
		return R.data(pages);
	}

	/**
	 * 新增
	 */
	@PostMapping("/save")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入newhost")
	public R save(@Valid @RequestBody Newhost newhost) {

		return R.status(newhostService.save(newhost));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入newhost")
	public R update(@Valid @RequestBody Newhost newhost) {
		return R.status(newhostService.updateById(newhost));
	}

	/**
	 * 新增或修改
	 */
	@PostMapping("/submit")
	@ApiOperationSupport(order = 6)
	@ApiOperation(value = "新增或修改", notes = "传入newhost")
	public R submit(@Valid @RequestBody Newhost newhost) {
		return R.status(newhostService.saveOrUpdate(newhost));
	}


	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(newhostService.deleteLogic(Func.toLongList(ids)));
	}
	@GetMapping("export-newhost")
	public void exportNotice(HttpServletResponse response) {
		List<NewhostExcel> list = new ArrayList<>();
/**
 * 		QueryWrapper<Region> queryWrapper = Condition.getQueryWrapper(region, Region.class);
 * 		List<RegionExcel> list = regionService.exportRegion(queryWrapper);
 * 		ExcelUtil.export(response, "行政区划数据" + DateUtil.time(), "行政区划数据表", list, RegionExcel.class);
 *
 */
		List<Newhost> newhostList = newhostService.list();
		NewhostExcel newhostExcel = new NewhostExcel();
		ZoneId zoneId = ZoneId.systemDefault();
		LocalDateTime localDateTime = LocalDateTime.now();



		for(Newhost nh:newhostList){


			newhostExcel.setAgentId(nh.getAgentId());
			newhostExcel.setCity(nh.getCity());
			newhostExcel.setEmail(nh.getEmail());
			newhostExcel.setGender(nh.getGender());
			newhostExcel.setJoinTime(DateUtil.now());
			newhostExcel.setProvince(nh.getProvince());
			newhostExcel.setWtsap(nh.getWtsap());
			newhostExcel.setRealName(nh.getRealName());
			newhostExcel.setLikeeId(nh.getLikeeId());
			newhostExcel.setUid(nh.getUid());
			list.add(newhostExcel);
		}


		ExcelUtil.export(response, "新增主播导出数据", "新增主播数据表", list, NewhostExcel
			.class);
	}

//	@PostMapping("write-newhost")
//	public R<Boolean> writeNotice(MultipartFile file) {
//		NewHostImporter importer = new NewHostImporter(newhostService);
//		ExcelUtil.save(file, importer,NewhostExcel.class);
//		return R.success("操作成功");
//	}

	/**
	 * 导入新增主播
	 * @param
	 */
	@PostMapping("import-newhost")
	public R importNewhost(MultipartFile file,Integer isCovered){
		NewHostImporter newHostImporter=new NewHostImporter(newhostService,isCovered==1);
		ExcelUtil.save(file, newHostImporter, NewhostExcel.class);
		return R.success("操作成功");
	}

	@GetMapping("export-template")
	//@ApiOperationSupport(order = 14)
	//@ApiOperation(value = "导出模板")
	public void exportUser(HttpServletResponse response) {
		List<NewhostExcel> list = new ArrayList<>();
		ExcelUtil.export(response, "新增数据模板", "用户数据表", list, NewhostExcel.class);
	}
}
