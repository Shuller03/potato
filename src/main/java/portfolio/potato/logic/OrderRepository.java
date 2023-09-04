package portfolio.potato.logic;

import org.springframework.data.repository.CrudRepository;
import portfolio.potato.model.PotatoOrder;

import java.sql.Date;
import java.util.List;

public interface OrderRepository extends CrudRepository<PotatoOrder, String> {

    PotatoOrder save(PotatoOrder order);

    List<PotatoOrder> findByDeliveryZip(String deliveryZip);
    List<PotatoOrder> readOrderByDeliveryZipAndPlacedAtBetween(String deliveryZip, Date startDate, Date endDate);
}
