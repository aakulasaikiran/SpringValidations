package com.lsn.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.lsn.domain.Customer;

@Controller
public class CustomerController {
	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
	private Map<Integer, Customer> customers = null;
	@Autowired
	@Qualifier("customerValidator")
	private Validator validator;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}

	public CustomerController() {
		customers = new HashMap<Integer, Customer>();
	}

	@ModelAttribute("customer")
	public Customer createCustomerModel() {
		// ModelAttribute value should be same as used in the empSave.jsp
		return new Customer();
	}

	@RequestMapping(value = "/customer", method = RequestMethod.GET)
	public String saveCustomerPage(Model model) {

		return "custSave";
	}

	@RequestMapping(value = "/customersave", method = RequestMethod.POST)
	public String saveEmployeeAction(@ModelAttribute("customer") @Validated Customer customer,
			BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			logger.info("Returning custSave.jsp page");

			return "custSave";
		}
		logger.info("Returning custSaveSuccess.jsp page");

		customers.put(customer.getId(), customer);

		return "custSaveSuccess";
	}
}