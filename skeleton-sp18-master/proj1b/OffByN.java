public class OffByN implements CharacterComparator {
    int off = 0;
    public OffByN(int N) {
        this.off = N;
    }

    @Override
    public boolean equalChars(char x, char y) {
        return x - y == off || x - y == -off;
    }
}
