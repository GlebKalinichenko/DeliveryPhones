package com.develop.gleb.deliveryphones.mvp.interfaces.sendphones;

import android.content.Context;

<<<<<<< HEAD:app/src/main/java/com/develop/gleb/deliveryphones/mvp/interfaces/sendphones/ISendPhoneModel.java
import com.develop.gleb.deliveryphones.PhoneEntity;
=======
<<<<<<< HEAD:app/src/main/java/com/example/gleb/deliveryphones/mvp/interfaces/sendphones/ISendPhoneModel.java
import com.example.gleb.deliveryphones.PhoneEntity;
=======
import com.develop.gleb.deliveryphones.ISendPhoneCallback;
import com.develop.gleb.deliveryphones.PhoneEntity;
import com.develop.gleb.deliveryphones.callbacks.ILoginCallback;
>>>>>>> 509e1eb... Refactor send screen by using injections:app/src/main/java/com/develop/gleb/deliveryphones/mvp/interfaces/sendphones/ISendPhoneModel.java
>>>>>>> c4ad6015c7df6bb0b5066e9e23a1c90028930779:app/src/main/java/com/example/gleb/deliveryphones/mvp/interfaces/sendphones/ISendPhoneModel.java

import java.util.List;

public interface ISendPhoneModel {
    List<PhoneEntity> getPhones(Context context);
    void pushPhones(List<PhoneEntity> phones, ISendPhoneCallback callback);
    List<PhoneEntity> getDatabasePhones();
<<<<<<< HEAD
    void clearPhones();
=======
<<<<<<< HEAD:app/src/main/java/com/example/gleb/deliveryphones/mvp/interfaces/sendphones/ISendPhoneModel.java
=======
    void clearPhones(ISendPhoneCallback callback);
>>>>>>> 509e1eb... Refactor send screen by using injections:app/src/main/java/com/develop/gleb/deliveryphones/mvp/interfaces/sendphones/ISendPhoneModel.java
>>>>>>> 41472d7abb6b32b33e291841c49b24a1b48e9637
}
