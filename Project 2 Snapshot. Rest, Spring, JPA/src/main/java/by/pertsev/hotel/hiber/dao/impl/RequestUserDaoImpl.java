package by.pertsev.hotel.hiber.dao.impl;

import by.pertsev.hotel.hiber.controller.exception.NotFoundException;
import by.pertsev.hotel.hiber.dao.RequestUserDao;
import by.pertsev.hotel.hiber.dao.proxy.ProxyRequestUserRepository;
import by.pertsev.hotel.hiber.model.RequestUser;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@AllArgsConstructor
@Transactional
public class RequestUserDaoImpl implements RequestUserDao {

    @PersistenceContext
    private final EntityManager entityManager;
    private final ProxyRequestUserRepository proxyRequestUserRepository;

    @Transactional
    @Override
    public RequestUser save(RequestUser entity) {
        proxyRequestUserRepository.save(entity);
        return entity;
    }

    @Transactional(readOnly = true)
    @Override
    public Page<RequestUser> findAll(Pageable pageable) {
        return proxyRequestUserRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public RequestUser findById(Integer id) {
        return proxyRequestUserRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public void update(RequestUser entity, Integer id) {
        entity.setRequestId(id);
        entityManager.merge(entity);
    }

    @Override
    public void deleteById(int id) {
        proxyRequestUserRepository.deleteById(id);
    }


}
