package restControllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import entity.Users;
import services.UserService;

@RestController
//@RequestMapping("/login")
@RequestMapping("/api/auth/public/signin")
public class LoginController {
	
	//DI UserService
	private final UserService userService;
	
	@Autowired
	public LoginController(UserService userService) {
		super();
		this.userService = userService;
	}



	@PostMapping
	public ResponseEntity<Map<String, Object>> login( @RequestBody Map<String, String> userPayload) {
		System.out.println("Login controller got hit");
		System.out.println("Users Payload: " + userPayload);
		//return "success";
		Users tempUsers = new Users();
		tempUsers.setEmail(userPayload.get("username"));
		tempUsers.setPassword(userPayload.get("password"));
		String jwtToken =  this.userService.verify(tempUsers);
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("jwtToken", jwtToken);
		return ResponseEntity.ok(data);
	}
	
}
