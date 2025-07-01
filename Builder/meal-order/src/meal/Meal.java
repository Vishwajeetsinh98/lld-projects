package meal;

public class Meal {
    private String mainItem;
    private String sideItem;
    private String drink;
    private String dessert;
    private String toy;
    private boolean isKidsMeal;

    public String getMainItem() {
        return mainItem;
    }

    public void setMainItem(String mainItem) {
        this.mainItem = mainItem;
    }

    public String getSideItem() {
        return sideItem;
    }

    public void setSideItem(String sideItem) {
        this.sideItem = sideItem;
    }

    public String getDrink() {
        return drink;
    }

    public void setDrink(String drink) {
        this.drink = drink;
    }

    public String getDessert() {
        return dessert;
    }

    public void setDessert(String dessert) {
        if (isKidsMeal) throw new UnsupportedOperationException("Cannot have dessert for kid's meal");
        this.dessert = dessert;
    }

    public String getToy() {
        return toy;
    }

    public void setToy(String toy) {
        if (!isKidsMeal) throw new UnsupportedOperationException("Cannot have toy if meal is not kid's meal");
        this.toy = toy;
    }

    public boolean isKidsMeal() {
        return isKidsMeal;
    }

    public void setKidsMeal(boolean kidsMeal) {
        if (dessert != null) throw new UnsupportedOperationException("Cannot be a kids meal since dessert is added");
        isKidsMeal = kidsMeal;
    }

    public String toString () {
        StringBuilder sb = new StringBuilder();
        if (isKidsMeal)
            sb.append ("[Kids]:\n");
        if (mainItem == null) return "ERROR! No main item.";
        sb.append("Main: ").append(mainItem).append("\n");
        if (sideItem != null)
            sb.append("Side: ").append(sideItem).append("\n");
        if (drink != null)
            sb.append("Drink: ").append(drink).append("\n");
        if (dessert != null)
            sb.append("Dessert: ").append(dessert).append("\n");
        if (isKidsMeal && toy != null)
            sb.append("Toy: ").append(toy).append("\n");
        sb.append("===============================================");
        return sb.toString();
    }
}
