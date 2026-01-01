package runner;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import entity.Role;
import entity.Users;
import repos.RoleRepo;
import repos.UserRepo;

@Component
public class DataInitalizer implements CommandLineRunner {
	// DI
	private final UserRepo userRepo;
	private final RoleRepo roleRepo;
	
	public DataInitalizer(UserRepo userRepo, RoleRepo roleRepo) {
		this.userRepo = userRepo;
		this.roleRepo = roleRepo;
	}
	
	private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
	@Override
	public void run(String... args) throws Exception {
		
		initRoles();
		initUsers();
	}

	private void initRoles() {
		
		long roleCount = roleRepo.count();
		
		Set<Role> roles = new HashSet<>();
		
		if(roleCount == 0) {
			Role adminRole = new Role();
			adminRole.setRole("ADMIN");
			
			Role userRole = new Role();
			userRole.setRole("USER");
			
			roles.add(userRole);
			roles.add(adminRole);
			
			roleRepo.saveAll(roles);
			
			System.out.println("Roles Initialized");
			
			return;
		}
		
		System.out.println("Roles Already Exists");

	}
	
	private void initUsers() {
		
		Users demoUser = new Users();
		Users demoAdmin = new Users();
		
		if(!userRepo.existsByEmail("demo@gmail.com")) {
			demoUser.addRole(createUser());
			demoUser.setPassword(encoder.encode("demo123"));
			demoUser.setCreatedDate(LocalDateTime.now());
			demoUser.setUsername("demouser");
			demoUser.setEmail("demo@gmail.com");
			userRepo.save(demoUser);
			System.out.println("Demo User Created");
		}

		if(!userRepo.existsByEmail("admin@gmail.com")) {
			demoAdmin.setRoles(createAdmin());
			demoAdmin.setPassword(encoder.encode("admin123"));
			demoAdmin.setCreatedDate(LocalDateTime.now());
			demoAdmin.setUsername("demoadmin");
			demoAdmin.setEmail("admin@gmail.com");
			userRepo.save(demoAdmin);
			System.out.println("Demo Admin Created");
		}
		
	}
	
	private Role createUser() {
		Role userRole = roleRepo.findByRole("USER").orElseThrow(()-> new RuntimeException("Role Not found"));
		return userRole;
	}
	
	private Set<Role> createAdmin() {
		// An Admin has all the roles
		Set<Role> adminRoles = new HashSet<>(roleRepo.findAll());
		return adminRoles;
	}
	
	

}
