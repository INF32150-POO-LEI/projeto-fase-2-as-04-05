package Product;

/**
 * Represents the types of packaging available.
 */
public enum PackagingType {
    BAG,
    BOX,
    CARD_BOX,
    PALLET;

    /**
     * Returns the code associated with the packaging type.
     *
     * @return the code of the packaging type
     */
    public int getCode() {
        switch (this) {
            case BAG:
                return 1001;

            case BOX:
                return 2002;

            case CARD_BOX:
                return 3003;

            case PALLET:
                return 4004;

            default:
                return 0;
        }
    }
}
