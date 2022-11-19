package com.spotrank.core.controllers;

import com.spotrank.core.dtos.UserDto;
import com.spotrank.core.dtos.WalletDto;
import com.spotrank.core.services.UserService;
import com.spotrank.core.services.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("wallet")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("{id}")
    public ResponseEntity<WalletDto> get(@PathVariable("id") Long id) {
        if(walletService.existsById(id))
            return new ResponseEntity<WalletDto>(walletService.findById(id), HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //USEFULL DURING DEVELOP, THIS WILL BE REMOVED WHEN TRANSACTIONS WILL BE IMPLEMENTED
    @PutMapping("balance")
    public ResponseEntity<WalletDto> editBalance(@RequestParam("id") Long id, @RequestParam("amount") int amount) {
        if(walletService.findById(id) != null) {
            return new ResponseEntity<WalletDto>(walletService.editBalance(id, amount), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
