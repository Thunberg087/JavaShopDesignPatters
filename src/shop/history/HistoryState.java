package shop.history;

public class HistoryState {
    HistoryCommand undo;
    HistoryCommand redo;

    public HistoryState(HistoryCommand h1, HistoryCommand h2) {
        this.undo = h1;
        this.redo = h2;
    }
}
