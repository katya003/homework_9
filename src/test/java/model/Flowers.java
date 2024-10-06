package model;

import java.util.List;

public class Flowers {
    private List<Flower> flowers;

    public List<Flower> getFlowers() {
        return flowers;
    }

    public static class Flower {
        private Integer number;
        private String name;
        private Integer quantity;

        public Integer getNumber() {
            return number;
        }

        public String getName() {
            return name;
        }

        public Integer getQuantity() {
            return quantity;
        }
    }
}
