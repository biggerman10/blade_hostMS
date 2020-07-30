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
package org.springblade.modules.host.service;

import org.springblade.modules.host.entity.Host;
import org.springblade.modules.host.excel.HostExcel;
import org.springblade.modules.host.vo.HostVO;
import org.springblade.core.mp.base.BaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.modules.newhost.excel.NewhostExcel;
import org.springframework.cache.annotation.CacheEvict;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 *  服务类
 *
 * @author BladeX
 * @since 2020-07-02
 */
public interface IHostService extends BaseService<Host> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param host
	 * @return
	 */
	IPage<HostVO> selectHostPage(IPage<HostVO> page, HostVO host);

	void importHost(List<HostExcel> data, Boolean isCovered);

	@Override
	@CacheEvict(value = "host")
	boolean saveOrUpdate(Host entity);

	@Override
	@CacheEvict(value = "host",allEntries = true)
	boolean deleteLogic(@NotEmpty List<Long> ids);
}
