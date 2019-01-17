package entities;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;

public interface EntityRoot {

    String getWebSafeKey();

    Key<? extends EntityRoot> getKey();

    Ref<? extends EntityRoot> getRef();

}
