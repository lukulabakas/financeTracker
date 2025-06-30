package com.lukulabakas.financeTracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class FinanceTrackerApplication {


	public static void main(String[] args) {
		SpringApplication.run(FinanceTrackerApplication.class, args);
	}

}
