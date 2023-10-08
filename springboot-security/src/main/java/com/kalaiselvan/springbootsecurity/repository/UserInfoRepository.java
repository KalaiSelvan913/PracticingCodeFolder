package com.kalaiselvan.springbootsecurity.repository;

import com.kalaiselvan.springbootsecurity.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserInfoRepository  extends JpaRepository<UserInfo, Integer> {

    Optional<UserInfo> findByName(String username);
}
