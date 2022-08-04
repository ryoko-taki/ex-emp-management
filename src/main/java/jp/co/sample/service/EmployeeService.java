package jp.co.sample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sample.domain.Employee;
import jp.co.sample.repository.EmployeeRepository;

@Service
@Transactional
public class EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;
	//リポジトリクラスのfindAll()で取得した従業員情報をリストに入れるメソッド
	public List<Employee> showList(){
		List<Employee> list = employeeRepository.findAll();
		return list;
	}
	//idから従業員の詳細情報を取得するメソッド
	public Employee showDetail(Integer id) {
		Employee employee = employeeRepository.load(id);
		return employee;
	}
}
