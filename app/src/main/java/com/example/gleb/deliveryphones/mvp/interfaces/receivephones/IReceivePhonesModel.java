package com.example.gleb.deliveryphones.mvp.interfaces.receivephones;

import android.content.Context;

<<<<<<< HEAD:app/src/main/java/com/example/gleb/deliveryphones/mvp/interfaces/receivephones/IReceivePhonesModel.java
import com.example.gleb.deliveryphones.PhoneEntity;
=======
import com.develop.gleb.deliveryphones.IReceivePhoneCallback;
import com.develop.gleb.deliveryphones.PhoneEntity;
>>>>>>> d035241... Refactor receive phones screen by using injections:app/src/main/java/com/develop/gleb/deliveryphones/mvp/interfaces/receivephones/IReceivePhonesModel.java

import java.util.List;

public interface IReceivePhonesModel {
<<<<<<< HEAD:app/src/main/java/com/example/gleb/deliveryphones/mvp/interfaces/receivephones/IReceivePhonesModel.java
    void receivePhones();
    void savePhones(Context context, List<PhoneEntity> entities);
<<<<<<< HEAD
    void clearPhones();
=======
=======
    void receivePhones(IReceivePhoneCallback callback);
    void savePhones(Context context, List<PhoneEntity> entities, IReceivePhoneCallback callback);
    void clearPhones(IReceivePhoneCallback callback);
>>>>>>> d035241... Refactor receive phones screen by using injections:app/src/main/java/com/develop/gleb/deliveryphones/mvp/interfaces/receivephones/IReceivePhonesModel.java
>>>>>>> 41472d7abb6b32b33e291841c49b24a1b48e9637
}
