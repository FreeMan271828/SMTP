package thread;

import data_object.Email;

import java.util.Collection;

public class SendTask {

    private Collection collection;
    //要求必须存在senderId,recceiverAddress,subject
    private Email email;




    public Collection getCollection() {
        return collection;
    }

    public void setCollection(Collection collection) {
        this.collection = collection;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }
}
