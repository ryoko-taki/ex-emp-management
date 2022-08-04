package jp.co.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Employee;
import jp.co.sample.form.UpdateEmployeeForm;
import jp.co.sample.service.EmployeeService;

//従業員情報を検索する
@Controller
@RequestMapping("/employee")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	
	@RequestMapping("/showList")
	public String showList(Model model) {
		employeeService.showList();
		model.addAttribute("employeeList", employeeService.showList());
		return "employee/list";
	}
	
	@ModelAttribute
	//扶養人数の更新の際にリクエストスコープに自動格納される
	public UpdateEmployeeForm setUpdateEmployeeForm() {
		UpdateEmployeeForm form = new UpdateEmployeeForm();
		return form;
	}
	@RequestMapping("/showDetail")
	public String showDetail(String id, Model model) {
		Employee employee = employeeService.showDetail(Integer.parseInt(id));
		model.addAttribute("employee", employee);
		return "employee/detail.html";
	}
}
