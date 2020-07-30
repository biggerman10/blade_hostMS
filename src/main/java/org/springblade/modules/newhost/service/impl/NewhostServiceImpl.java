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
package org.springblade.modules.newhost.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.modules.newhost.entity.Newhost;
import org.springblade.modules.newhost.excel.NewhostExcel;
import org.springblade.modules.newhost.vo.NewhostVO;
import org.springblade.modules.newhost.mapper.NewhostMapper;
import org.springblade.modules.newhost.service.INewhostService;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.modules.system.entity.User;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 *  服务实现类
 *
 * @author BladeX
 * @since 2020-06-27
 */
@Service
public class NewhostServiceImpl extends BaseServiceImpl<NewhostMapper, Newhost> implements INewhostService {

	@Override
	public IPage<NewhostVO> selectNewhostPage(IPage<NewhostVO> page, NewhostVO newhost) {
		return page.setRecords(baseMapper.selectNewhostPage(page, newhost));
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void importNewhost(List<NewhostExcel> data, Boolean isCovered) {
		data.forEach(newhostExcel -> {
			Newhost newhost = Objects.requireNonNull(BeanUtil.copy(newhostExcel, Newhost.class));
			if(isCovered){
				Newhost oldNewhost=this.getOne(new QueryWrapper<Newhost>().eq("uid",newhostExcel.getUid()));
				if (oldNewhost != null && oldNewhost.getId() != null) {
					newhost.setId(oldNewhost.getId());
					this.updateById(newhost);
					return;
				}
			}

			this.save(newhost);
		});
	}

}
