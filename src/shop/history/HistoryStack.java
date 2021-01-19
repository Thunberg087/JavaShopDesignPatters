package shop.history;

import java.util.Stack;

public class HistoryStack {

    static Stack<HistoryState> redoStack = new Stack<>();
    static Stack<HistoryState> undoStack = new Stack<>();


    public static void undo() {
        if (redoStack.size() > 0) {
            redoStack.push(undoStack.peek());
            undoStack.pop().undo.execute();
        }
    }

    public static void redo() {
        if (redoStack.size() > 0) {
            undoStack.push(redoStack.peek());
            redoStack.pop().redo.execute();
        }
    }

    public static void addHistoryState(HistoryState historyState) {
        undoStack.push(historyState);
        redoStack.clear();
    }
}

