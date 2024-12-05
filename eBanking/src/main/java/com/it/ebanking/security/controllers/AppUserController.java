package com.it.ebanking.security.controllers;


import com.it.ebanking.security.dtos.AppUserDTO.CreateAppUserDTO;
import com.it.ebanking.security.dtos.AppUserDTO.ResponseAppUserDTO;
import com.it.ebanking.security.dtos.AppUserDTO.UpdateAppUserDTO;
import com.it.ebanking.security.services.interfaces.IAppUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Validated
@RestController
@RequestMapping("/api")
public class AppUserController {

    @Autowired
    private IAppUserService appUserService;

    @PostMapping("/public/register")
    public ResponseEntity<ResponseAppUserDTO> createAppUser(@Valid @RequestBody CreateAppUserDTO createAppUserDTO) {
        ResponseAppUserDTO appUser = appUserService.create(createAppUserDTO);
        return new ResponseEntity<>(appUser, HttpStatus.OK);
    }


    @GetMapping("/admin/users/{username}")
    public ResponseEntity<ResponseAppUserDTO> getAppUserById( @PathVariable("username") String username) {
        ResponseAppUserDTO user = appUserService.getByUsername(username);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/admin/users")
    public ResponseEntity<List<ResponseAppUserDTO>> getAllUsers() {
        List<ResponseAppUserDTO> users = appUserService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/public/notices")
    public ResponseEntity<String> getNotices() {
        return new ResponseEntity<>("Voici les annonces générales du système.", HttpStatus.OK);
    }

    @GetMapping("/user/myCards")
    public ResponseEntity<String> getMyCards() {
        return new ResponseEntity<>("Voici vos cartes bancaires liées.", HttpStatus.OK);
    }

    @GetMapping("/user/myLoans")
    public ResponseEntity<String> getMyLoans() {
        return new ResponseEntity<>("Informations sur vos prêts.", HttpStatus.OK);
    }

    @GetMapping("/user/myAccount")
    public ResponseEntity<String> getMyAccount() {
        return new ResponseEntity<>("Détails de votre compte.", HttpStatus.OK);
    }

    @GetMapping("/user/myBalance")
    public ResponseEntity<String> getMyBalance() {
        return new ResponseEntity<>("Votre solde global est de 1234,56 €.", HttpStatus.OK);
    }

    @GetMapping("/public/contact")
    public ResponseEntity<String> getContact() {
        return new ResponseEntity<>("Support client : support@example.com, +1 234 567 890.", HttpStatus.OK);
    }

    @DeleteMapping("/admin/users/{username}")
    public ResponseEntity<String> deleteUser(@PathVariable("username") String username) {
        appUserService.deleteUser(username);
        return new ResponseEntity<>("Utilisateur est supprimé avec succès", HttpStatus.OK);
    }

    @PutMapping("/admin/users/{username}")
    public ResponseEntity<ResponseAppUserDTO> updateAppUser( @PathVariable("username") String username, @Valid @RequestBody UpdateAppUserDTO updateAppUserDTO) {

        ResponseAppUserDTO updatedAppUser = appUserService.updateUser(username, updateAppUserDTO);
        return new ResponseEntity<>(updatedAppUser, HttpStatus.OK);
    }
}

