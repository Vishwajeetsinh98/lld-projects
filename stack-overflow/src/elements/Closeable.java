package elements;

public interface Closeable {
    public void voteToClose();
    public void close();
    public int getVotesToClose();
}
