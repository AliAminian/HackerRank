package cashe;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LRUCache<K, V> implements Cache<K, V> {
    private int size;
    private Map<K, LinkedListNode<CacheElement<K, V>>> listOfHittingInfo;
    private DoublyLinkedList<CacheElement<K, V>> listOfSavedNodes;
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public LRUCache(int size) {
        this.size = size;
        this.listOfHittingInfo = new ConcurrentHashMap<>(size);
        this.listOfSavedNodes = new DoublyLinkedList<>();
    }

    @Override
    public boolean put(K key, V value) {
        this.lock.writeLock().lock();
        try {
            CacheElement<K, V> item = new CacheElement<K, V>(key, value);
            LinkedListNode<CacheElement<K, V>> newNode;
            if (this.listOfHittingInfo.containsKey(key)) {
                LinkedListNode<CacheElement<K, V>> node = this.listOfHittingInfo.get(key);
                newNode = listOfSavedNodes.updateAndMoveToFront(node, item);
            } else {
                if (this.size() >= this.size) {
                    this.evictElement();
                }
                newNode = this.listOfSavedNodes.add(item);
            }
            if (newNode.isEmpty()) {
                return false;
            }
            this.listOfHittingInfo.put(key, newNode);
            return true;
        } finally {
            this.lock.writeLock().unlock();
        }
    }

    @Override
    public Optional<V> get(K key) {
        this.lock.readLock().lock();
        try {
            LinkedListNode<CacheElement<K, V>> linkedListNode = this.listOfHittingInfo.get(key);
            if (linkedListNode != null && !linkedListNode.isEmpty()) {
                listOfHittingInfo.put(key, this.listOfSavedNodes.moveToFront(linkedListNode));
                return Optional.of(linkedListNode.getElement().getValue());
            }
            return Optional.empty();
        } finally {
            this.lock.readLock().unlock();
        }
    }

    @Override
    public int size() {
        this.lock.readLock().lock();
        try {
            return listOfSavedNodes.size();
        } finally {
            this.lock.readLock().unlock();
        }
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public void clear() {
        this.lock.writeLock().lock();
        try {
            listOfHittingInfo.clear();
            listOfSavedNodes.clear();
        } finally {
            this.lock.writeLock().unlock();
        }
    }


    private boolean evictElement() {
        this.lock.writeLock().lock();
        try {
            LinkedListNode<CacheElement<K, V>> linkedListNode = listOfSavedNodes.removeTail();
            if (linkedListNode.isEmpty()) {
                return false;
            }
            listOfHittingInfo.remove(linkedListNode.getElement().getKey());
            return true;
        } finally {
            this.lock.writeLock().unlock();
        }
    }
}
