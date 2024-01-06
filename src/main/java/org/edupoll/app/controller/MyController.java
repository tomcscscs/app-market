package org.edupoll.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@RequestMapping("/my")
@Controller
@RequiredArgsConstructor
public class MyController {

	
	@GetMapping("/picks") 
	public String showPersonalPicks() {
		return "my/picks";
	}
}
