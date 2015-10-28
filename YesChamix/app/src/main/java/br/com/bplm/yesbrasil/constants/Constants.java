package br.com.bplm.yesbrasil.constants;

import android.os.Build;

/**
 * Created by lucas on 19/09/15.
 */
public interface Constants {

    String IMAGE_SUFFIX_JPG = ".jpg";
    String IMAGE_SUFFIX_PNG = ".png";
    String IMAGE_SUFFIX_GIF = ".gif";
    String BACKGROUND_TASK_DEFAULT_ID = "BACKGROUND_TASK_DEFAULT_ID";

    boolean IS_EMULATOR = Build.FINGERPRINT.startsWith("generic");

    /**
     * Configs the rest
     */
    interface Network {
        int CONNECTION_TIMEOUT = 20 * 1000;
        int SOCKET_TIMEOUT = 0;
    }

    /**
     * Configs the intent
     */
    interface Intent {
        String NEXT_ACTIVITY = "next_activity";
        String NEXT_REGISTER_ACTIVITY = "next_register_activity";
        String REGISTER_EMAIL = "register_email";
        String APP_NAME = "br.com.dafiti";
        String LOGIN_LOCATION = "loginLocation";
    }

    /**
     * String of the errors most usual
     */
    interface ApiErrors {
        String ERROR_DEFAULT = "Ooops! Houve um erro inesperado. Por favor, tente novamente.";
    }


}
