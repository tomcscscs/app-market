package org.edupoll.app.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.edupoll.app.model.Account;
import org.edupoll.app.model.UpdateInfo;
import org.edupoll.app.model.UpdateLocation;
import org.edupoll.app.repository.AccountsRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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

	@PostMapping("/settings/profile/info")
	public String proceedUpdateInfo(@ModelAttribute UpdateInfo updateInfo, @SessionAttribute Account logonAccount,
			HttpSession session) throws IllegalStateException, IOException  {
		
		Account one = Account.builder().id(logonAccount.getId()).nickname(updateInfo.getNickname()).build();
		
		if(!updateInfo.getProfileImage().isEmpty()) {
			File dir = new File("c:\\upload\\profileImage\\", logonAccount.getId());
			dir.mkdirs();
			// String fileName =UUID.randomUUID().toString().replace("-","") + ".jpg";
			File target = new File(dir, "img.jpg");
			updateInfo.getProfileImage().transferTo(target);
			one.setProfileImageUrl("/upload/profileImage/"+logonAccount.getId()+"/img.jpg");
		}
		
		accountsRepository.update(one);
		
		session.setAttribute("logonAccount", accountsRepository.findById(logonAccount.getId()));
		
		
		
		return "redirect:/settings/profile";
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
