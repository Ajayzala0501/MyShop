package com.projects.myshop.Service.Impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.projects.myshop.Service.RegistrationService;
import com.projects.myshop.config.Token;
import com.projects.myshop.emailservice.EmailDetails;
import com.projects.myshop.emailservice.EmailService;
import com.projects.myshop.enitity.Registration;
import com.projects.myshop.model.LoginModel;
import com.projects.myshop.model.RegistrationModel;
import com.projects.myshop.model.ResetPasswordModel;
import com.projects.myshop.repository.RegistrationRepository;

@Service
public class RegistrationServiceImpl implements RegistrationService {

	@Autowired
	RegistrationRepository registrationRepository;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	EmailService emailService;

	@Override
	public Registration addRegistration(RegistrationModel model) {
		// TODO Auto-generated method stub
		Registration re = new Registration();
		re.setUsername(model.getUsername());
		re.setEmail(model.getEmail());
		re.setPassword(bCryptPasswordEncoder.encode(model.getPassword()));
		return registrationRepository.save(re);
	}

	@Override
	public Optional<Registration> checkEmailAlreadyExits(String email, String username) {
		return registrationRepository.findByEmailAndUsername(email, username);
	}

	@Override
	public Optional<Registration> checkLogin(LoginModel loginModel) {
		// TODO Auto-generated method stub
		return registrationRepository.findByEmailAndPassword(loginModel.getEmail(), loginModel.getPassword());
	}

	@Override
	public Optional<Registration> findByemail(String email) {
		// TODO Auto-generated method stub
		return registrationRepository.findByEmail(email);
	}

	public Registration saveData(Registration registration) {
		return registrationRepository.save(registration);
	}

	@Override
	public Boolean verifyToken(String token) {
		Optional<Registration> reg = registrationRepository.findByToken(token);
		if (reg.isPresent()) {
			Date d = new Date();
			if (d.compareTo(reg.get().getTokenExpirationTime()) < 0) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	@Override
	public Boolean resetPassword(ResetPasswordModel model) {
		// TODO Auto-generated method stub
		Optional<Registration> reg = registrationRepository.findByToken(model.getToken());
		if (reg.isPresent()) {
			reg.get().setPassword(bCryptPasswordEncoder.encode(model.getPassword()));
			Registration data = registrationRepository.save(reg.get());
			if (data != null) {
				return true;
			} else {
				return false;
			}
		}

		return false;
	}

	@Override
	public String ForgotPasswordProcessImpl(Registration checkEmail, HttpServletRequest request) {
		// TODO Auto-generated method stub
		Token t = Token.generateNewToken();
		checkEmail.setToken(t.getToken());
		checkEmail.setTokenExpirationTime(t.getExpriteToken());
		registrationRepository.save(checkEmail);

//			EmailDetails details = new EmailDetails(model.getEmail(),)
		String msgBody = mailMessage(applicationURL(t, request, "/verifyToken?Token="));
		String res = emailService
				.sendMailWithAttachment(new EmailDetails(checkEmail.getEmail(), msgBody, "Verify Your E-mail Address"));
		return res;
	}

	public String applicationURL(Token token, HttpServletRequest request, String url) {
		return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + url
				+ token.getToken();
	}

	public String mailMessage(String url) {
		String content = "";
		try {
			StringBuilder bldr = new StringBuilder();
			String str = "";
			BufferedReader in = new BufferedReader(
					new FileReader("../myshop/src/main/resources/static/email_templates/action.html"));
			while ((str = in.readLine()) != null)
				bldr.append(str);
			in.close();
			content = bldr.toString();
			content = content.replace("{{Header}}", "Email Verification").replace("{{Content}}",
					"You're almost ready to get started. Please click on the button below to verify your email address and enjoy exclusive cleaning services with us!")
					.replace("{{url}}", url).replace("{{Button-Name}}", "VERIFY YOUR EMAIL");
			in.close();
		} catch (IOException e) {
		}
		return content;
	}

	@Override
	public String saveTemporaryDetailsAndTokenGeneration(RegistrationModel model, HttpServletRequest request) {
		// TODO Auto-generated method stub
		Token t = Token.generateNewToken();
		HttpSession ses = request.getSession();
		ses.setAttribute(t.getToken(), t);
		ses.setAttribute("RegistrationDetails", model);

		String msgBody = mailMessage(applicationURL(t, request, "/verifyTokenForRegistration?Token="));
		String res = emailService
				.sendMailWithAttachment(new EmailDetails(model.getEmail(), msgBody, "Verify Your E-mail Address"));
		return res;
	}

	@Override
	public Boolean verifyTokenForRegistration(String token, HttpServletRequest request) {
		// TODO Auto-generated method stub
		HttpSession ses = request.getSession();
		Token t = (Token) ses.getAttribute(token);
		Date d = new Date();
		if (t != null && d.compareTo(t.getExpriteToken()) < 0) {
			RegistrationModel reg = (RegistrationModel) ses.getAttribute("RegistrationDetails");
			if (reg != null) {
				Registration re = new Registration();
				re.setUsername(reg.getUsername());
				re.setEmail(reg.getEmail());
				re.setPassword(bCryptPasswordEncoder.encode(reg.getPassword()));
				Registration reSave = registrationRepository.save(re);
				if (reSave != null) {
					return true;
				} else {
					return false;
				}

			} else {
				return false;
			}
		}
		return false;
	}

}
