package tests.framework;

import base.Deletable;
import base.Registry;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class RegistryTest {

    private Deletable object1;
    private Deletable object2;
    private Deletable object3;
    private Registry registry;
    private Registry trash;

    @Before
    public void setup() {
        object1 = new TestDeletable("id1");
        object2 = new TestDeletable("id2");
        object3 = new TestDeletable("id3");
        trash = new Registry();
    }

    @Test
    public void testGetData() {
        registry = createRegistry();
        assertThat(registry.getData(), contains(object1, object2, object3));
    }

    @Test
    public void testAdd() {
        registry = createRegistry();
        Deletable object4 = new TestDeletable("id4");
        registry.add(object4);
        assertThat(registry.getData(), contains(object1, object2, object3, object4));
    }

    @Test
    public void testEmpty() {
        registry = new Registry();
        assertThat(registry.getData(), is(empty()));
    }

    @Test
    public void testCleanup() {
        registry = createRegistry();
        registry.cleanUp();
        assertThat(registry.getData(), is(empty()));
    }

    @Test
    public void testCleanupOrder() {
        registry = createRegistry();
        registry.cleanUp();
        assertThat(trash.getData(), contains(object3, object2, object1));
    }

    @Test
    public void testCleanupAndAdd() {
        registry = createRegistry();
        registry.cleanUp();
        Deletable object = new TestDeletable("id9");
        registry.add(object);
        assertThat(registry.getData(), contains(object));
    }

    @Test
    public void testGetDataModification() {
        registry = createRegistry();
        List<Deletable> data = registry.getData();
        data.remove(1);
        assertThat(data, contains(object1, object3));
        assertThat(registry.getData(), contains(object1, object2, object3));
    }


    private Registry createRegistry() {
        registry = new Registry();
        registry.add(object1);
        registry.add(object2);
        registry.add(object3);
        return registry;
    }

    class TestDeletable implements Deletable {

        String id;

        TestDeletable(String id) {
            this.id = id;
        }

        @Override
        public String getId() {
            return id;
        }

        @Override
        public void delete() {
            trash.add(this);
        }
    }
}
