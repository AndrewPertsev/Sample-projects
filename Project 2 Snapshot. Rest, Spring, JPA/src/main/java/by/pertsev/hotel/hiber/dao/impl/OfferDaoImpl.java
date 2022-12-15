package by.pertsev.hotel.hiber.dao.impl;

import by.pertsev.hotel.hiber.controller.exception.NotFoundException;
import by.pertsev.hotel.hiber.dao.OfferDao;
import by.pertsev.hotel.hiber.dao.proxy.ProxyOfferRepository;
import by.pertsev.hotel.hiber.model.Offer;
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
public class OfferDaoImpl implements OfferDao {

    @PersistenceContext
    private EntityManager entityManager;
    private final ProxyOfferRepository proxyOfferRepository;

    @Override
    public Offer save(Offer entity) {
        proxyOfferRepository.save(entity);
        return entity;
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Offer> findAll(Pageable pageable) {
        return proxyOfferRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public Offer findById(Integer id) {
        return proxyOfferRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public void update(Offer entity, Integer id) {
        entity.setOfferId(id);
        entityManager.merge(entity);
    }

    @Override
    public void deleteById(int id) {
        proxyOfferRepository.deleteById(id);
    }


//    @Override
//    public Page<Offer> findOffersByUserId(int userId, Pageable pageable) {
//        return proxyOfferRepository.findOffersByUserId(userId, pageable);
//    }
}
