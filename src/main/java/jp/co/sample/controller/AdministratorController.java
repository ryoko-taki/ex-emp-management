package jp.co.sample.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import jp.co.sample.domain.Administrator;
import jp.co.sample.form.InsertAdministratorForm;
import jp.co.sample.form.LoginForm;
import jp.co.sample.service.AdministratorService;

@Controller
@RequestMapping("/")
public class AdministratorController {
	@Autowired
	private AdministratorService administratorService;
	
	@ModelAttribute
	public InsertAdministratorForm setUpInsertAdministratorForm() {
		InsertAdministratorForm form = new InsertAdministratorForm();
		return form;
	}
	
	@RequestMapping("/toInsert")
	public String toInsert() {
		return "administrator/insert.html";
	}
	
	@RequestMapping("/insert")
	public String insert(InsertAdministratorForm form) {
		Administrator administrator = new Administrator();
		administrator.setName(form.getName());
		administrator.setMailAddress(form.getMailAddress());
		administrator.setPassword(form.getPassword());
		administratorService.insert(administrator);
		return "redirect:/";
	}
	//入力されて受け取った値＝リクエストパラメータ
	//→LoginFormに格納
	 //→Modelオブジェクト＝リクエストスコープ(データ領域)　に格納
	@ModelAttribute
	public LoginForm setUpLoginForm() {
		LoginForm loginform = new LoginForm();
		return loginform;
	}
	
	@RequestMapping("/")
	public String toLogin() {
		return "administrator/login";
	}
	
	@Autowired
	private HttpSession session;
	@RequestMapping("/login")
	public String login(LoginForm form, Model model) {
		Administrator administrator = administratorService.login(form.getMailAddress(),form.getPassword());
		if(administrator == null) {
			model.addAttribute("message","メールアドレスまたはパスワードが不正です。");
			return "administrator/login";
		}else {
			session.setAttribute("administratorName", administrator.getMailAddress());
			return "forward:/employee/showList";
		}
	}
	
}
