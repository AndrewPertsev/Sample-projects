package by.pertsev.hotel.hiber.dao.proxy;

import by.pertsev.hotel.hiber.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProxyCategoryRepository extends JpaRepository<Category, Integer> {
}
