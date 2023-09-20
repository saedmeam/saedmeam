package bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCTPRICE")
public class ProductPrice {
	private Integer idProductPrice;

	private String idProduct;
	private Double incrementPrice;
	private Integer maxDuesNo;

	public ProductPrice() {

	}

	public ProductPrice(String idProduct, Double incrementPrice, Integer maxDuesNo) {
		this.idProduct = idProduct;
		this.incrementPrice = incrementPrice;
		this.maxDuesNo = maxDuesNo;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_product_price", nullable = false)
	public Integer getIdProductPrice() {
		return idProductPrice;
	}

	public void setIdProductPrice(Integer idProductPrice) {
		this.idProductPrice = idProductPrice;
	}

	@Column(name = "id_product", nullable = false)

	public String getIProduct() {
		return idProduct;
	}

	public void setIdProduct(String idProduct) {
		this.idProduct = idProduct;
	}

	@Column(name = "increment_price", nullable = false)
	public Double getIncrementPrice() {
		return incrementPrice;
	}

	public void setIncrementPrice(Double incrementPrice) {
		this.incrementPrice = incrementPrice;
	}

	@Column(name = "max_dues_no", nullable = false)
	public Integer getMaxDuesNo() {
		return maxDuesNo;
	}

	public void setMaxDuesNo(Integer maxDuesNo) {
		this.maxDuesNo = maxDuesNo;
	}

}
