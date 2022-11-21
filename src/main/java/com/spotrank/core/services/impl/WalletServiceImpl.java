package com.spotrank.core.services.impl;

import com.spotrank.core.dtos.UserDto;
import com.spotrank.core.dtos.WalletDto;
import com.spotrank.core.mappers.UserMapper;
import com.spotrank.core.mappers.WalletMapper;
import com.spotrank.core.models.User;
import com.spotrank.core.models.Wallet;
import com.spotrank.core.repositories.UserRepository;
import com.spotrank.core.repositories.WalletRepository;
import com.spotrank.core.services.UserService;
import com.spotrank.core.services.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class WalletServiceImpl implements WalletService {

    @Autowired
    WalletRepository repository;

    @Override
    public WalletDto findByOwner(Long id) {
        return null;
    }

    @Override
    public WalletDto findById(Long id) {
        return WalletMapper.INSTANCE.toWalletDTO(repository.findById(id).orElse(null));
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public WalletDto create(User user) {
        if(!repository.findByOwner(user).isPresent()){
            Wallet newWallet = new Wallet();
            newWallet.setBalance(0);
            newWallet.setOwner(user);
            Wallet createdWallet = repository.save(newWallet);
            return WalletMapper.INSTANCE.toWalletDTO(createdWallet);
        }
        return null;
    }

    @Override
    public WalletDto editBalance(Long id, int amount) {
        Wallet walletToEdit = repository.findById(id).orElse(null);
        walletToEdit.setBalance(walletToEdit.getBalance() + amount);
        walletToEdit = repository.save(walletToEdit);
        return WalletMapper.INSTANCE.toWalletDTO(walletToEdit);
    }

}
