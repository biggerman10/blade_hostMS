package org.springblade.modules.gift.excel;

import lombok.RequiredArgsConstructor;
import org.springblade.core.excel.support.ExcelImporter;
import org.springblade.modules.gift.entity.HostGift;
import org.springblade.modules.gift.service.IHostGiftService;
import org.springblade.modules.host.excel.HostExcel;
import org.springblade.modules.host.service.IHostService;


import java.util.List;
@RequiredArgsConstructor
public class HostGiftImporter implements ExcelImporter<GiftExcel> {

		private final IHostGiftService service;


		@Override
		public void save(List<GiftExcel> data) {

			service.importGift(data);
		}
	}
