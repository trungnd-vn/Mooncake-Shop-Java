/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trungnd.cart;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author HOME
 */
public class CartObj {
    private Map<String, CakeInCart> cakeList;
    private float total;

    public Map<String, CakeInCart> getCakeList() {
        return cakeList;
    }

    public void setCakeList(Map<String, CakeInCart> cakeList) {
        this.cakeList = cakeList;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public CartObj() {
    }
    
    public void addItem(String id, String name, double price, String image) {
        if (this.cakeList == null) {
            this.cakeList = new HashMap<>();
            this.total = 0;
        }
        CakeInCart info = new CakeInCart(name, 1, price, image);
        total += price;
        if (this.cakeList.containsKey(id)) {
            info.setQuantity(this.cakeList.get(id).getQuantity() + 1);
            info.setPrice(this.cakeList.get(id).getPrice() + price);
        }
        this.cakeList.put(id, info);
    }
    
    public void subItem(String id, String name, double price, String image) {
        if (this.cakeList.get(id).getQuantity() == 1) {
            removeItem(id);
            return;
        }
        CakeInCart info = new CakeInCart(name, 1, price, image);
        total -= price;
        if (this.cakeList.containsKey(id)) {
            info.setQuantity(this.cakeList.get(id).getQuantity() - 1);
            info.setPrice(this.cakeList.get(id).getPrice() - price);
        }
        this.cakeList.put(id, info);
    }

    public void removeItem(String id) {
        if (this.cakeList == null) {
            total = 0;
            return;
        }
        if (this.cakeList.containsKey(id)) {
            double p = this.cakeList.get(id).getPrice();
            this.total -= p;
            this.cakeList.remove(id);
            if (this.cakeList.isEmpty()) {
                this.cakeList = null;
            }
        }
    }

}
