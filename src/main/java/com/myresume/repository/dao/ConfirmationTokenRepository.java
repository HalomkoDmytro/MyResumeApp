package com.myresume.repository.dao;

import com.myresume.entity.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, String> {

    ConfirmationToken findByConfirmationToken(String confirmationToken);
}
