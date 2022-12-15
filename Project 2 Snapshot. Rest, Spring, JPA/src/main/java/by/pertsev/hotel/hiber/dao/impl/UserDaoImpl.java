package by.pertsev.hotel.hiber.dao.impl;

import by.pertsev.hotel.hiber.controller.exception.NotFoundException;
import by.pertsev.hotel.hiber.dao.UserDao;
import by.pertsev.hotel.hiber.dao.proxy.ProxyUserRepository;
import by.pertsev.hotel.hiber.model.User;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
@AllArgsConstructor
@Transactional
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private final EntityManager entityManager;
    private final ProxyUserRepository proxyUserRepository;

    @Override
    public User save(User entity) {
        proxyUserRepository.save(entity);
        return entity;
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return proxyUserRepository.findByLogin(login);
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return proxyUserRepository.findAll(pageable);
    }

    @Override
    public User findById(Integer id) {
        return proxyUserRepository.findById(id).orElseThrow(NotFoundException::new);
    }


    @Override
    public void update(User entity, Integer id) {
        entity.setUserId(id);
        entityManager.merge(entity);
    }

    @Override
    public void deleteById(int id) {
        proxyUserRepository.deleteById(id);
    }


}
