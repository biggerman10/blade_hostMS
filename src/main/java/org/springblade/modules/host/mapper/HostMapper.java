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
package org.springblade.modules.host.mapper;

import org.springblade.modules.host.entity.Host;
import org.springblade.modules.host.vo.HostVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 *  Mapper 接口
 *
 * @author BladeX
 * @since 2020-07-02
 */
public interface HostMapper extends BaseMapper<Host> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param host
	 * @return
	 *
	 * 1) @Cacheable与redis整合时, 使用的是redis的hset数据类型.
	 *
	 *    @Cacheable的语法:
	 *
	 * @Cacheable(value=”testcache”,key=”#userName”)
	 *     redis命令行客户端使用hset的语法是:
	 *
	 * hset key field value
	 * 2)  @Cacheable的value参数实际上就是hset命令的key字段, 而@Cacheable的key参数是hset的field字段,
	 *
	 * 最后@Cacheable注解所在的函数返回值是hset命令的value字段
	 */
	@Cacheable(cacheNames = "hosts")
	List<HostVO> selectHostPage(IPage page, HostVO host);

}
