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
        return mapper.mapList(userRepository.getAllUsers(), UserDTO.class);
    }

    @Transactional
    public UserDTO getUser(long id) {
        UserEntity userEntity = userRepository.getUser(id);
        return mapper.map(userEntity, UserDTO.class);
    }

    @Transactional
    public UserDTO addUser(UserCreateDTO userCreateDTO) {
        UserEntity userEntity = mapper.map(userCreateDTO, UserEntity.class);
        long userId = userRepository.addUser(userEntity);
        return mapper.map(userRepository.getUser(userId), UserDTO.class);
    }

    @Transactional
    public List<UserDTO> deleteUser(long id) {
        userRepository.deleteUser(id);
        return mapper.mapList(userRepository.getAllUsers(), UserDTO.class);
    }

    @Transactional
    public UserDTO updateUser(long userId, UserUpdateDTO dto) {
        UserEntity userEntity = mapper.map(dto, UserEntity.class);
        userEntity.setId(userId);
        userRepository.updateUser(userEntity);
        return mapper.map(userRepository.getUser(userId), UserDTO.class);
    }

    @Transactional
    public Set<BankAccountDTO> getUserBankAccounts(long userId) {
        UserEntity userEntity = userRepository.getUser(userId);
        return mapper.mapSet(userEntity.getBankAccountEntityList(), BankAccountDTO.class);
    }

    @Transactional
    public BankAccountDTO getUserBankAccount(long userId, long bankAccountId) {
        return mapper.map(bankAccountsRepository.getUserBankAccount(userId, bankAccountId), BankAccountDTO.class);
    }

    @Transactional
    public Set<BankAccountDTO> addUserBankAccount(long id, BankAccountCreateDTO bankAccount) {
        UserEntity userEntity = userRepository.getUser(id);
        userEntity.addBankAccount(mapper.map(bankAccount, BankAccountEntity.class));
        return mapper.mapSet(userEntity.getBankAccountEntityList(), BankAccountDTO.class);
    }

    @Transactional
    public BankAccountDTO updateUserBankAccount(long userId, long bankAccountId, BankAccountUpdateDTO dto) {
        BankAccountEntity entity = mapper.map(dto, BankAccountEntity.class);
        entity.setId(bankAccountId);
        bankAccountsRepository.updateUserBankAccount(entity);
        return mapper.map(bankAccountsRepository.getUserBankAccount(userId, bankAccountId), BankAccountDTO.class);
    }

    @Transactional
    public Set<BankAccountDTO> deleteUserBankAccount(long userId, long bankAccountId) {
        UserEntity userEntity = userRepository.getUser(userId);
        if (userEntity != null) {
            BankAccountEntity bankAccountEntity = bankAccountsRepository.getBankAccount(bankAccountId);
            if (bankAccountEntity != null) {
                userEntity.removeBankAccount(bankAccountEntity);
            }
            return getUserBankAccounts(userId);
        }
        return new HashSet<>();
    }

    @Transactional
    public BankAccountDTO addCard(long bankAccountId, long cardId) {
        CardEntity cardEntity = cardRepository.getCard(cardId);
        BankAccountEntity bankAccountEntity = bankAccountsRepository.getBankAccount(bankAccountId);
        bankAccountEntity.addCard(cardEntity);
        return mapper.map(bankAccountEntity, BankAccountDTO.class);
    }

    @Transactional
    public BankAccountDTO removeCard(long bankAccountId, long cardId) {
        CardEntity cardEntity = cardRepository.getCard(cardId);
        BankAccountEntity bankAccountEntity = bankAccountsRepository.getBankAccount(bankAccountId);
        bankAccountEntity.removeCard(cardEntity);
        return mapper.map(bankAccountEntity, BankAccountDTO.class);
    }
}
