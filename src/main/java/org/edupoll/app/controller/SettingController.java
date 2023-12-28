package org.edupoll.app.controller;

import javax.servlet.http.HttpSession;

import org.edupoll.app.model.Account;
import org.edupoll.app.model.UpdateLocation;
import org.edupoll.app.repository.AccountsRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class SettingController {
	private final AccountsRepository accountsRepository;

	@GetMapping({ "/settings", "/settings/profile" })
	public String showSettings() {

		return "settings/form";
	}

	@PutMapping("/settings/profile/location")
	public String proceedUpdateLocation(@ModelAttribute UpdateLocation updateLocation,
			@SessionAttribute Account logonAccount, HttpSession session) {
		Account one = Account.builder().id(logonAccount.getId()).address(updateLocation.getAddress())
				.latitude(updateLocation.getLatitude()).longitude(updateLocation.getLongitude()).build();
		
		accountsRepository.update(one);
		
		session.setAttribute("logonAccount", accountsRepository.findById(logonAccount.getId()));
		
		return "redirect:/settings/profile";
	}
}





