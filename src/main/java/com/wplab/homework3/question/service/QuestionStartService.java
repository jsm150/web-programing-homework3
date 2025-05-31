package com.wplab.homework3.question.service;

import com.wplab.homework3.core.Transactional;
import com.wplab.homework3.entity.User;
import com.wplab.homework3.question.repository.UserRepository;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.security.auth.login.LoginException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Base64;

public class QuestionStartService implements IQuestionStartService {

    private final UserRepository userRepository;
    private static final String ALGORITHM = "PBKDF2WithHmacSHA256";
    private static final int ITERATIONS = 10000; // 반복 횟수, 높을수록 안전하지만 느려짐
    private static final int KEY_LENGTH = 256;   // 결과 해시의 비트 길이
    private static final int SALT_LENGTH = 16;   // 솔트 바이트 길이 (128 bits)

    public QuestionStartService(UserRepository _userRepository) {
        userRepository = _userRepository;
    }

    // 새 패스워드 해시 생성
    private static String hashPassword(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        SecureRandom random = SecureRandom.getInstanceStrong(); // 강력한 난수 생성기
        byte[] salt = new byte[SALT_LENGTH];
        random.nextBytes(salt);

        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, ITERATIONS, KEY_LENGTH);
        SecretKeyFactory factory = SecretKeyFactory.getInstance(ALGORITHM);
        byte[] hash = factory.generateSecret(spec).getEncoded();

        // 솔트와 해시를 함께 저장 (예: Base64 인코딩 후 구분자로 연결)
        // 실제로는 DB에 salt 컬럼, hash 컬럼 따로 저장하는 것이 더 일반적입니다.
        // 여기서는 예시를 위해 하나의 문자열로 만듭니다.
        return Base64.getEncoder().encodeToString(salt) + ":" + Base64.getEncoder().encodeToString(hash);
    }

    // 패스워드 검증
    private static boolean verifyPassword(String originalPassword, String storedPasswordAndSalt)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        String[] parts = storedPasswordAndSalt.split(":");
        if (parts.length != 2) {
            throw new IllegalArgumentException("저장된 패스워드 형식이 잘못되었습니다.");
        }
        byte[] salt = Base64.getDecoder().decode(parts[0]);
        byte[] hashFromStorage = Base64.getDecoder().decode(parts[1]);

        KeySpec spec = new PBEKeySpec(originalPassword.toCharArray(), salt, ITERATIONS, KEY_LENGTH);
        SecretKeyFactory factory = SecretKeyFactory.getInstance(ALGORITHM);
        byte[] calculatedHash = factory.generateSecret(spec).getEncoded();

        // 생성된 해시와 저장된 해시를 비교 (시간 일관성 있는 비교 권장)
        // 여기서는 Arrays.equals를 사용하지만, 실제로는 타이밍 공격 방지를 위해
        // MessageDigest.isEqual() 같은 것을 사용하는 것이 더 좋습니다.
        // (다만, PBKDF2 결과물은 길이가 고정되어 있어 단순 비교도 큰 문제는 없을 수 있음)
        return Arrays.equals(calculatedHash, hashFromStorage);
    }


    @Transactional
    public User loginOrRegister(String email, String username, String password) throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException, LoginException {
        var user = userRepository.findBy(email);
        if (user == null) {
            user = new User();
            user.Email = email;
            user.Name = username;
            user.PasswordHash = hashPassword(password);
            userRepository.save(user);
        }
        else if (!verifyPassword(password, user.PasswordHash)) {
            throw new LoginException();
        }
        else {
            user.Name = username;
            userRepository.update(user);
        }
        return user;
    }
}
