package org.springblade.modules.gift.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

import java.util.Date;

/**
 * @author bigger
 */

@Data
@ColumnWidth(25)
@HeadRowHeight(20)
@ContentRowHeight(18)
public class GiftExcel {

	@ColumnWidth(20)
	@ExcelProperty("Cause Time")
	@DateTimeFormat("yyyy/MM/dd")
	private Date causeTime;
	@ExcelProperty("Host UID")
	@ColumnWidth(20)
	private String uid;
	@ColumnWidth(20)
	@ExcelProperty("Agent Number")
	private String agentId;
	@ColumnWidth(20)
	@ExcelProperty("Type")
	private Integer category;
}
