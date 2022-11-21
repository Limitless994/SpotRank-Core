package com.spotrank.core.services;

import com.spotrank.core.dtos.UserDto;
import com.spotrank.core.dtos.WalletDto;
import com.spotrank.core.models.User;
import com.spotrank.core.models.Wallet;

import java.util.List;

public interface WalletService {
    WalletDto findByOwner(Long id);
    WalletDto findById(Long id);
    boolean existsById(Long id);
    void delete(Long id);
    WalletDto create(User user);
    WalletDto editBalance(Long id, int amount);
}
