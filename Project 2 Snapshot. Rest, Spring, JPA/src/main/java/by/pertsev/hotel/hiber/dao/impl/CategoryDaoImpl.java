package by.pertsev.hotel.hiber.dao.impl;

import by.pertsev.hotel.hiber.controller.exception.NotFoundException;
import by.pertsev.hotel.hiber.dao.CategoryDao;
import by.pertsev.hotel.hiber.dao.proxy.ProxyCategoryRepository;
import by.pertsev.hotel.hiber.model.Category;
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
public class CategoryDaoImpl implements CategoryDao {

    @PersistenceContext
    private final EntityManager entityManager;
    private final ProxyCategoryRepository proxyCategoryRepository;

    @Override
    public Category save(Category entity) {
        proxyCategoryRepository.save(entity);
        return entity;
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Category> findAll(Pageable pageable) {
        return proxyCategoryRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public Category findById(Integer id) {
        return proxyCategoryRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public void update(Category entity, Integer id) {
        entity.setCategoryId(id);
        entityManager.merge(entity);
    }

    @Override
    public void deleteById(int id) {
        proxyCategoryRepository.deleteById(id);
    }


}