package org.springblade.modules.newhost.excel;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@ColumnWidth(25)
@HeadRowHeight(20)
@ContentRowHeight(18)
public class NewhostExcel {

	@ColumnWidth(20)
	@ExcelProperty("Agent Number")
	private String agentId;
	@ColumnWidth(20)
	@ExcelProperty("Sign with us")
	@DateTimeFormat("yyyy/MM/dd")
	private Date joinTime;
	@ExcelProperty("Host UID")
	@ColumnWidth(20)
	private String uid;
	@ColumnWidth(20)
	@ExcelProperty("LIKEE ID")
	private String likeeId;
	@ColumnWidth(10)
	@ExcelProperty("Gender")
	private String gender;
	@ColumnWidth(20)
	@ExcelProperty("Real Name")
	private String realName;
	@ColumnWidth(20)
	@ExcelProperty("WhatsApp")
	private String wtsap;
	@ColumnWidth(20)
	@ExcelProperty("Email")
	private String email;
	@ColumnWidth(15)
	@ExcelProperty("Province")
	private String province;
	@ColumnWidth(10)
	@ExcelProperty("City")
	private String city;


}
