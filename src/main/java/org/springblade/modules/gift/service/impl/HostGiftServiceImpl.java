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
package org.springblade.modules.gift.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.modules.gift.entity.HostGift;
import org.springblade.modules.gift.excel.GiftExcel;

import org.springblade.modules.gift.vo.HostGiftVO;
import org.springblade.modules.gift.mapper.HostGiftMapper;
import org.springblade.modules.gift.service.IHostGiftService;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.modules.host.entity.Host;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;
import java.util.Objects;

/**
 *  服务实现类
 *
 * @author BladeX
 * @since 2020-07-06
 */
@Service
public class HostGiftServiceImpl extends BaseServiceImpl<HostGiftMapper, HostGift> implements IHostGiftService {

	@Override
	public IPage<HostGiftVO> selectHostGiftPage(IPage<HostGiftVO> page, HostGiftVO hostGift) {
		return page.setRecords(baseMapper.selectHostGiftPage(page, hostGift));
	}

	@Override
	public void importGift(List<GiftExcel> data) {
		data.forEach(giftExcel -> {
			HostGift gift = Objects.requireNonNull(BeanUtil.copy(giftExcel, HostGift.class));
			this.save(gift);
		});
	}

}
