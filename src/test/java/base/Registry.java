package base;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Keeps the inserted {@link Deletable} objects.
 *
 * The class has a capability to delete the stored objects in reverse order. The main purpose of the class is
 * to take care of the safe deletion of data created during the test execution.
 */
public final class Registry {

    private List<Deletable> data = new LinkedList<>();

    /**
     * Stores the provided object to registry
     *
     * @param object Deletable object to be stored
     */
    public void add(Deletable object) {
        data.add(object);
    }

    /**
     * Clear out all the stored objects in reverse order
     */
    public void cleanUp() {
        Collections.reverse(data);
        for (Deletable object : data) {
            deleteObject(object);
        }
        data.clear();
    }

    /**
     * Provides the safe copy of stored data
     *
     * @return The stored data
     */
    public List<Deletable> getData() {
        return new LinkedList<>(data);
    }

    private void deleteObject(Deletable object) {
        try {
            object.delete();
        } catch (Exception e) {
            // do not throw any exception - all the objects in registry have to be deleted
            String message = String.format("Could not delete object: %s [%s]", object.getId(), object.getClass().getName());
            System.out.println(message);
            e.printStackTrace();
        }
    }
}
