package org.springblade.modules.newhost.excel;

import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Var;
import org.springblade.core.excel.support.ExcelImporter;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.modules.desk.entity.Notice;
import org.springblade.modules.newhost.entity.Newhost;
import org.springblade.modules.newhost.service.INewhostService;

import java.util.List;

@RequiredArgsConstructor
public class NewHostImporter implements ExcelImporter<NewhostExcel> {

	private final INewhostService service;
	private final Boolean isCovered;

	@Override
	public void save(List<NewhostExcel> data) {

		service.importNewhost(data, isCovered);
	}
}
