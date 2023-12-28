package org.edupoll.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SettingController {
	
	@GetMapping({"/settings", "/setting/profile"})
	public String showSettings() {
		
		return "settings/form";
	}
}
