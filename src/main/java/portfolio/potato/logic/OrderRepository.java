package portfolio.potato.logic;

import org.springframework.data.repository.CrudRepository;
import portfolio.potato.model.PotatoOrder;

public interface OrderRepository extends CrudRepository<PotatoOrder, String> {
    PotatoOrder save(PotatoOrder order);
}
