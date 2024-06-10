package com.warehouse.admin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class WarehouseController {

	@GetMapping("/warehouse")
	public String getStoreHouse() {
		return "warehouse formed";
}
}
