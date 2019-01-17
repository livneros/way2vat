package repo;


import com.googlecode.objectify.Key;
import com.googlecode.objectify.cmd.Query;
import entities.EntityRoot;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import static com.googlecode.objectify.ObjectifyService.ofy;
import utils.Constants;
import utils.Utils;

public class Repo {

    public Repo(){
    }

    public <T> List<T> load(Class<T> type) {
        return ofy().load().type(type).list();
    }

    public <T> List<T> load(Class<T> type, int limit, String orderBy){
        return ofy().load().type(type).order(orderBy).limit(limit).list();
    }
    public <T> List<T> load(Class<T> type, int limit) {
        return ofy().load().type(type).limit(limit).list();
    }

    private <T> T load(Key<T> key) {
        return ofy().load().key(key).safe();
    }

    public <T> T load(String webSafeKey) {
        Utils.checkNotNull(webSafeKey, Constants.EXPECTED_WEB_SAFE_KEY_GOT_NULL);
        return load(Key.<T>valueOf(webSafeKey));
    }

    public <T> Iterable<T> load(Class<T> type, int fromIndex, int toIndex) {
        return load(ofy().load().type(type).keys().list().subList(fromIndex, toIndex));
    }

    private <T> Collection<T> load(Collection<Key<T>> keys) {
        return ofy().load().keys(keys).values();
    }

    public <T> List<T> load(List<String> webSafeKeys) {
        Collection<Key<T>> keys = new ArrayList<>();
        for (String webSafeKey : webSafeKeys) {
            keys.add(Key.<T>create(webSafeKey));
        }
        return new ArrayList<>(load(keys));
    }

    public <T> Key<T> save(T entity) {
        return ofy().save().entity(entity).now();
    }

    public <T> Iterable<Key<T>> save(Collection<T> entities) {
        return ofy().save().entities(entities).now().keySet();
    }

    public <T> void delete(Key<T> key) {
        ofy().delete().key(key).now();
    }
    public <T> void delete(String key) {
        ofy().delete().key(Key.<T>create(key)).now();
    }

    public <T extends EntityRoot> void delete(T entity) {
        ofy().delete().key(entity.getKey()).now();
    }

    public <T> void delete(Collection<Key<T>> keys) {
        ofy().delete().keys(keys).now();
    }

    private <T> Query<T> getQuery(Class<T> type) {
        return ofy().load().type(type);
    }

    public <T> List<T> load(Class<T> type, FilterPair... filters) {
        Query<T> query = getQuery(type);
        for (Pair<String, Object> filter : filters) {
            query = query.filter(filter.getLeft(), filter.getRight());
        }
        return query.list();
    }

    public <T> List<T> load(Class<T> type, int limit,  FilterPair... filters) {
        Query<T> query = getQuery(type);
        for (Pair<String, Object> filter : filters) {
            query = query.filter(filter.getLeft(), filter.getRight());
        }
        return query.limit(limit).list();
    }

    public static class FilterPair extends Pair<String, Object> {

        private final String first;
        private final Object second;

        public FilterPair(String first, Object second) {
            this.first = first;
            this.second = second;
        }


        @Override
        public String getLeft() {
            return first;
        }

        @Override
        public Object getRight() {
            return second;
        }


        @Override
        public Object setValue(Object o) {
            return null;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;
            FilterPair that = (FilterPair) o;
            return Objects.equals(first, that.first) &&
                    Objects.equals(second, that.second);
        }

        @Override
        public int hashCode() {
            return Objects.hash(super.hashCode(), first, second);
        }
    }

}

