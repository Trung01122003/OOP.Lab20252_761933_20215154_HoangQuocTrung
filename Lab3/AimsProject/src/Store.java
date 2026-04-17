public class Store {
    public static final int MAX_ITEMS_IN_STORE = 1000;
    private DigitalVideoDisc itemsInStore[] = new DigitalVideoDisc[MAX_ITEMS_IN_STORE];
    private int qtyInStore = 0;

    public void addDVD(DigitalVideoDisc disc) {
        if (qtyInStore < MAX_ITEMS_IN_STORE) {
            itemsInStore[qtyInStore] = disc;
            qtyInStore++;
            System.out.println("The disc " + disc.getTitle() + " has been added to the store");
        } else {
            System.out.println("The store is full. Cannot add." + disc.getTitle());
        }
    }

    public void removeDVD(DigitalVideoDisc disc) {
        for (int i = 0; i < qtyInStore; i++) {
            if (itemsInStore[i] == disc) {
                for (int j = i; j < qtyInStore - 1; j++) {
                    itemsInStore[j] = itemsInStore[j + 1];
                }
                itemsInStore[qtyInStore - 1] = null;
                qtyInStore--;
                System.out.println("The disc " + disc.getTitle() + " has been removed from the store");
                return;
            }
        }
        System.out.println("The disc was not found in the store");
    }
}
