package repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import entity.PasswordResetToken;
import entity.Users;
import jakarta.transaction.Transactional;

@Repository
public interface PasswordRestTokenRepo extends JpaRepository<PasswordResetToken, Long> {

	Optional<PasswordResetToken> findByToken(String token);
	
	 @Transactional // derived query from the method name and not noraml crud, hence @Transactional needed here
	int deleteByUser(Users user); 
	//int deleteByUser_Id(Long userId);

}
