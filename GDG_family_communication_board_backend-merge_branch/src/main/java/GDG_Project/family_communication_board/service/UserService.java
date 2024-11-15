package GDG_Project.family_communication_board.service;

import GDG_Project.family_communication_board.model.User;
import GDG_Project.family_communication_board.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // 회원가입
    public User registerUser(User user) {
        return userRepository.save(user);
    }

    // 로그인 (아이디와 비밀번호 체크)
    public Optional<User> login(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            return user;
        } else {
            return Optional.empty();
        }
    }
}
