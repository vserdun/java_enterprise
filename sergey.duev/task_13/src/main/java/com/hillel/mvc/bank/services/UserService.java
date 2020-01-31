package com.hillel.mvc.bank.services;

import com.hillel.mvc.bank.dao.BankAccountsRepository;
import com.hillel.mvc.bank.dao.CardRepository;
import com.hillel.mvc.bank.dao.UserRepository;
import com.hillel.mvc.bank.models.dto.*;
import com.hillel.mvc.bank.models.entities.BankAccountEntity;
import com.hillel.mvc.bank.models.entities.CardEntity;
import com.hillel.mvc.bank.models.entities.UserEntity;
import com.hillel.mvc.bank.utils.Mapper;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {

    private UserRepository userRepository;
    private BankAccountsRepository bankAccountsRepository;
    private CardRepository cardRepository;
    private Mapper mapper;

    @Autowired
    public UserService(UserRepository userRepository, BankAccountsRepository bankAccountsRepository, CardRepository cardRepository, Mapper mapper) {
        this.userRepository = userRepository;
        this.bankAccountsRepository = bankAccountsRepository;
        this.cardRepository = cardRepository;
        this.mapper = mapper;
    }

    @Transactional
    public List<UserDTO> getAllUsers() {
        return mapper.mapList(userRepository.findAll(), UserDTO.class);
    }

    @Transactional
    public UserDTO getUser(long id) {
        UserEntity userEntity = userRepository.findById(id).get();
        return mapper.map(userEntity, UserDTO.class);
    }

    @Transactional
    public UserDTO addUser(UserCreateDTO userCreateDTO) {
        UserEntity userEntity = mapper.map(userCreateDTO, UserEntity.class);
        userEntity = userRepository.save(userEntity);
        return mapper.map(userEntity, UserDTO.class);
    }

    @Transactional
    public List<UserDTO> deleteUser(long id) {
        userRepository.deleteById(id);
        return mapper.mapList(userRepository.findAll(), UserDTO.class);
    }

    @Transactional
    public UserDTO updateUser(long userId, UserUpdateDTO dto) {
        UserEntity userEntity = mapper.map(dto, UserEntity.class);
        userEntity.setId(userId);
        userEntity = userRepository.save(userEntity);
        return mapper.map(userEntity, UserDTO.class);
    }

    @Transactional
    public Set<BankAccountDTO> getUserBankAccounts(long userId) {
        UserEntity userEntity = userRepository.findById(userId).get();
        return mapper.mapSet(userEntity.getBankAccountEntityList(), BankAccountDTO.class);
    }

    @Transactional
    public BankAccountDTO getUserBankAccount(long userId, long bankAccountId) {
        return mapper.map(bankAccountsRepository.getUserBankAccount(userId, bankAccountId), BankAccountDTO.class);
    }

    @Transactional
    public Set<BankAccountDTO> addUserBankAccount(long id, BankAccountCreateDTO bankAccount) {
        UserEntity userEntity = userRepository.findById(id).get();
        userEntity.addBankAccount(mapper.map(bankAccount, BankAccountEntity.class));
        return mapper.mapSet(userEntity.getBankAccountEntityList(), BankAccountDTO.class);
    }

    @Transactional
    public BankAccountDTO updateUserBankAccount(long userId, long bankAccountId, BankAccountUpdateDTO dto) {
        BankAccountEntity entity = mapper.map(dto, BankAccountEntity.class);
        entity.setId(bankAccountId);
        entity = bankAccountsRepository.save(entity);
        return mapper.map(entity, BankAccountDTO.class);
    }

    @Transactional
    public Set<BankAccountDTO> deleteUserBankAccount(long userId, long bankAccountId) {
        UserEntity userEntity = userRepository.findById(userId).get();
        if (userEntity != null) {
            BankAccountEntity bankAccountEntity = bankAccountsRepository.findById(bankAccountId).get();
            if (bankAccountEntity != null) {
                userEntity.removeBankAccount(bankAccountEntity);
            }
            return getUserBankAccounts(userId);
        }
        return new HashSet<>();
    }

    @Transactional
    public BankAccountDTO addCard(long bankAccountId, long cardId) {
        CardEntity cardEntity = cardRepository.findById(cardId).get();
        BankAccountEntity bankAccountEntity = bankAccountsRepository.findById(bankAccountId).get();
        bankAccountEntity.addCard(cardEntity);
        return mapper.map(bankAccountEntity, BankAccountDTO.class);
    }

    @Transactional
    public BankAccountDTO removeCard(long bankAccountId, long cardId) {
        CardEntity cardEntity = cardRepository.findById(cardId).get();
        BankAccountEntity bankAccountEntity = bankAccountsRepository.findById(bankAccountId).get();
        bankAccountEntity.removeCard(cardEntity);
        return mapper.map(bankAccountEntity, BankAccountDTO.class);
    }
}
