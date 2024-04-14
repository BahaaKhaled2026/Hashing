public interface PerfectHashTable {
    boolean search(int key);
    void insert(int key);
    void delete(int key);
    int getSpaceUsage();
    int getCollisionCount();
}
