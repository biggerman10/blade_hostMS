package org.springblade.modules.host.excel;

import lombok.RequiredArgsConstructor;
import org.springblade.core.excel.support.ExcelImporter;
import org.springblade.modules.host.service.IHostService;
import org.springblade.modules.newhost.excel.NewhostExcel;
import org.springblade.modules.newhost.service.INewhostService;

import java.util.List;

@RequiredArgsConstructor
public class HostImporter implements ExcelImporter<HostExcel> {

	private final IHostService service;
	private final Boolean isCovered;

	@Override
	public void save(List<HostExcel> data) {

		service.importHost(data, isCovered);
	}
}
