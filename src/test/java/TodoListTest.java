import org.example.TodoList;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TodoListTest {


    @Test
    void addAndList() {
        TodoList t = new TodoList();
        t.add(" task1 ");
        assertEquals(1, t.size());
        assertEquals("task1", t.getAll().get(0));
    }

    @Test
    void remove() {
        TodoList t = new TodoList();
        t.add("a");
        t.add("b");
        assertTrue(t.remove(0));
        assertEquals(1, t.size());
        assertFalse(t.remove(10));
    }

    @Test
    void addEmptyIgnored() {
        TodoList t = new TodoList();
        t.add(" ");
        assertEquals(0, t.size());
    }

    @Test
    void clearRemovesAllAndResetsStatus() {
        TodoList t = new TodoList();
        t.add("a");
        t.add("b");
        t.markDone(0);
        t.clear();

        assertEquals(0, t.size());
        assertFalse(t.isDone(0));
        assertTrue(t.getAll().isEmpty());
    }

    @Test
    void markDoneMarksCorrectTask() {
        TodoList t = new TodoList();
        t.add("a");
        t.add("b");

        assertTrue(t.markDone(1));
        assertTrue(t.isDone(1));
        assertFalse(t.isDone(0));

        assertFalse(t.markDone(-1));
        assertFalse(t.markDone(5));
    }

    @Test
    void removeKeepsDoneIndicesConsistent() {
        TodoList t = new TodoList();
        t.add("a");
        t.add("b");
        t.add("c");
        t.markDone(2);

        t.remove(0);

        assertTrue(t.isDone(1));
        assertFalse(t.isDone(2));
        assertEquals(2, t.size());
    }

    @Test
    void searchFindsMatchesCaseInsensitive() {
        TodoList t = new TodoList();
        t.add("buy milk");
        t.add("buy bread");
        t.add("walk dog");

        List<String> found = t.search("BUY");
        assertEquals(2, found.size());
        assertTrue(found.contains("buy milk"));
        assertTrue(found.contains("buy bread"));
    }

    @Test
    void searchReturnsEmptyOnNoMatchOrEmptyQuery() {
        TodoList t = new TodoList();
        t.add("task");

        assertTrue(t.search("zzz").isEmpty());
        assertTrue(t.search("   ").isEmpty());
        assertTrue(t.search(null).isEmpty());
    }
}