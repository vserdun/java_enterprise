package model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Data
@Component
public class ClientRepository {
    private HashMap<Integer, BankClient> clients;

}
