public class Cart {
    public static final int MAX_NUMBERS_ORDERED = 20;
    private DigitalVideoDisc itemsOrdered[] =
            new DigitalVideoDisc[MAX_NUMBERS_ORDERED];
    private int qtyOrdered = 0;

    public void addDigitalVideoDisc(DigitalVideoDisc disc) {
        if (qtyOrdered < MAX_NUMBERS_ORDERED) {
            itemsOrdered[qtyOrdered] = disc;
            qtyOrdered++;
            System.out.println("The disc has been added");
        } else {
            System.out.println("The cart is almost full");
        }
    }

    public void addDigitalVideoDisc(DigitalVideoDisc[] dvdList) {
        for (DigitalVideoDisc disc : dvdList) {
            if (qtyOrdered < MAX_NUMBERS_ORDERED) {
                itemsOrdered[qtyOrdered] = disc;
                qtyOrdered++;
                System.out.println("The disc " + disc.getTitle() + " has been added");
            } else {
                System.out.println("The cart is almost full. Cannot add " + disc.getTitle());
            }
        }
    }

    // Varargs version:
    // public void addDigitalVideoDisc(DigitalVideoDisc... dvdList) {
    //     for (DigitalVideoDisc disc : dvdList) {
    //         if (qtyOrdered < MAX_NUMBERS_ORDERED) {
    //             itemsOrdered[qtyOrdered++] = disc;
    //             System.out.println("The disc " + disc.getTitle() + " has been added");
    //         } else {
    //             System.out.println("The cart is almost full. Cannot add " + disc.getTitle());
    //         }
    //     }
    // }

    public void addDigitalVideoDisc(DigitalVideoDisc dvd1, DigitalVideoDisc dvd2) {
        if (qtyOrdered < MAX_NUMBERS_ORDERED) {
            itemsOrdered[qtyOrdered++] = dvd1;
            System.out.println("The disc " + dvd1.getTitle() + " has been added");
        } else {
            System.out.println("The cart is almost full. Cannot add " + dvd1.getTitle());
        }
        
        if (qtyOrdered < MAX_NUMBERS_ORDERED) {
            itemsOrdered[qtyOrdered++] = dvd2;
            System.out.println("The disc " + dvd2.getTitle() + " has been added");
        } else {
            System.out.println("The cart is almost full. Cannot add " + dvd2.getTitle());
        }
    }

    public void removeDigitalVideoDisc(DigitalVideoDisc disc) {
        for (int i = 0; i < qtyOrdered; i++) {
            if (itemsOrdered[i] == disc) {
                // Shift elements to the left to fill the gap
                for (int j = i; j < qtyOrdered - 1; j++) {
                    itemsOrdered[j] = itemsOrdered[j + 1];
                }
                itemsOrdered[qtyOrdered - 1] = null;
                qtyOrdered--;
                System.out.println("The disc has been removed");
                return;
            }
        }
        System.out.println("The disc was not found in the cart");
    }

    public float totalCost() {
        float sum = 0;
        for (int i = 0; i < qtyOrdered; i++) {
            sum += itemsOrdered[i].getCost();
        }
        return sum;
    }
}
