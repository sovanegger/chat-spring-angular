package cz.prf.uai.tomsovsky.chat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	@RequestMapping(value={"/", "/index", "/home"})
	public String homePage() {
		return "/clientside/index.html";
	}
}
