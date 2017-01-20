package com.example.gleb.deliveryphones.mvp.interfaces.sendphones;

import android.content.Context;

<<<<<<< HEAD:app/src/main/java/com/example/gleb/deliveryphones/mvp/interfaces/sendphones/ISendPhoneModel.java
import com.example.gleb.deliveryphones.PhoneEntity;
=======
import com.develop.gleb.deliveryphones.ISendPhoneCallback;
import com.develop.gleb.deliveryphones.PhoneEntity;
import com.develop.gleb.deliveryphones.callbacks.ILoginCallback;
>>>>>>> 509e1eb... Refactor send screen by using injections:app/src/main/java/com/develop/gleb/deliveryphones/mvp/interfaces/sendphones/ISendPhoneModel.java

import java.util.List;

public interface ISendPhoneModel {
    List<PhoneEntity> getPhones(Context context);
    void pushPhones(List<PhoneEntity> phones, ISendPhoneCallback callback);
    List<PhoneEntity> getDatabasePhones();
<<<<<<< HEAD:app/src/main/java/com/example/gleb/deliveryphones/mvp/interfaces/sendphones/ISendPhoneModel.java
=======
    void clearPhones(ISendPhoneCallback callback);
>>>>>>> 509e1eb... Refactor send screen by using injections:app/src/main/java/com/develop/gleb/deliveryphones/mvp/interfaces/sendphones/ISendPhoneModel.java
}
