package com.example.student_api.userApi.service;

import com.example.student_api.common.exception.DuplicateResourceException;
import com.example.student_api.common.exception.ResourceNotFoundException;
import com.example.student_api.userApi.dto.ProfileDto;
import com.example.student_api.userApi.dto.UserDetailDto;
import com.example.student_api.userApi.entity.Profile;
import com.example.student_api.userApi.entity.User;
import com.example.student_api.userApi.mapper.UserMapper;
import com.example.student_api.userApi.repository.ProfileRepository;
import com.example.student_api.userApi.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private static final Logger log= LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public ProfileDto createProfile(Long userId, ProfileDto dto) {
        User user=userRepository.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User not found"+userId));

        // application-level guard — the DB's UNIQUE constraint is the safety net behind this
        if(profileRepository.existsByUserId(user.getId())){
            throw new DuplicateResourceException("User already exists" +userId);
        }

        Profile profile=new  Profile();
        profile.setBio(dto.getBio());
        profile.setPhoneNumber(dto.getPhoneNumber());
        profile.setUser(user);

        Profile saved=profileRepository.save(profile);
        log.info("Created profile id={} for user id={}", saved.getId(), userId);

        return userMapper.toDto(saved);
    }

    @Override
    @Transactional
    public UserDetailDto  getUserWithProfile(Long userId) {
        User user=userRepository.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User not found"+userId));
        // accessing user.getProfile() here, inside @Transactional, avoids LazyInitializationException — same Q11 lesson
        return userMapper.toDetailDto(user);
    }
}
