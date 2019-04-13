package base;

/**
 * Represents deletable object
 */
public interface Deletable {

    /**
     * Returns the object identifier
     * @return Object identifier
     */
    String getId();

    /**
     * Executes the deletion of the object
     */
    void delete();
}
