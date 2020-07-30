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
package org.springblade.modules.host.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springblade.core.cache.utils.CacheUtil;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.modules.host.entity.Host;
import org.springblade.modules.host.excel.HostExcel;
import org.springblade.modules.host.vo.HostVO;
import org.springblade.modules.host.mapper.HostMapper;
import org.springblade.modules.host.service.IHostService;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.modules.newhost.entity.Newhost;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;
import java.util.Objects;

/**
 *  服务实现类
 *
 * @author BladeX
 * @since 2020-07-02
 */
@Service
public class HostServiceImpl extends BaseServiceImpl<HostMapper, Host> implements IHostService {

	@Override

	public IPage<HostVO> selectHostPage(IPage<HostVO> page, HostVO host) {
		return page.setRecords(baseMapper.selectHostPage(page, host));
	}

	@Override
	public void importHost(List<HostExcel> data, Boolean isCovered) {
		data.forEach(hostExcel -> {
			Host host = Objects.requireNonNull(BeanUtil.copy(hostExcel, Host.class));
			if(isCovered){

				Host oldHost=this.getOne(new QueryWrapper<Host>().eq("uid",hostExcel.getUid()));
				if (oldHost != null && oldHost.getId() != null) {
					host.setId(oldHost.getId());
					this.updateById(host);
					return;
				}
			}

			this.save(host);
		});
	}

}
