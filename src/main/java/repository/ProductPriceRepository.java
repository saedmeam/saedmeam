package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bean.ProductPrice;

@Repository

public interface ProductPriceRepository extends JpaRepository<ProductPrice, Integer>{

}
