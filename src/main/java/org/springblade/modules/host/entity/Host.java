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
package org.springblade.modules.host.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springblade.core.mp.base.BaseEntity;
import java.time.LocalDate;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 实体类
 *
 * @author BladeX
 * @since 2020-07-02
 */
@Data
@TableName("blade_host")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Host对象", description = "Host对象")
public class Host extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private String gender;
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy/MM/dd")
	private Date joinTime;
	private String uid;
	private String likeeId;
	private String realName;
	private String wtsap;
	private String email;
	private String province;
	private String city;
	private String agentId;



}
