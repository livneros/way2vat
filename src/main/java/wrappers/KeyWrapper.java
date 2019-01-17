package wrappers;

import entities.EntityRoot;

public class KeyWrapper {
    private String key;

    private KeyWrapper(String key) {
        this.key = key;
    }

    public KeyWrapper() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public static <T extends EntityRoot> KeyWrapper From(T entityRoot){
        return new KeyWrapper(entityRoot.getWebSafeKey());
    }
    public static KeyWrapper From(String key){
        return new KeyWrapper(key);
    }
}
