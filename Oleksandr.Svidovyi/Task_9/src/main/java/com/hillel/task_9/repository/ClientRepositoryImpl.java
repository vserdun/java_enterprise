package com.hillel.task_9.repository;

import com.hillel.task_9.model.ClientEntity;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class ClientRepositoryImpl implements ClientRepository {
    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public ClientEntity getClientById(int id) {
        Session session = sessionFactory.getCurrentSession();

        ClientEntity clientEntity = session.get(ClientEntity.class, id, LockMode.PESSIMISTIC_WRITE);
        return clientEntity;
    }


    @Override
    public List<ClientEntity> getClientsList() {
        Session session = sessionFactory.getCurrentSession();

        List<ClientEntity> clients = session.createQuery("from ClientEntity", ClientEntity.class).list();
        return clients;
    }

    @Override
    public void save(ClientEntity clientEntity) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(clientEntity);
    }


    @Override
    public void deleteClientById(int id) {
        Session session = sessionFactory.getCurrentSession();

        Query q = session.createQuery("delete from ClientEntity where id = :id");
        q.setParameter("id", id);
        q.executeUpdate();
    }
}
