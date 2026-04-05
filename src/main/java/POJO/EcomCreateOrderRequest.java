package POJO;

import java.util.List;

public class EcomCreateOrderRequest {
    private List<EcomCreateOrderSubRequest> orders;

    public List<EcomCreateOrderSubRequest> getOrders() {
        return orders;
    }

    public void setOrders(List<EcomCreateOrderSubRequest> orders) {
        this.orders = orders;
    }
}
