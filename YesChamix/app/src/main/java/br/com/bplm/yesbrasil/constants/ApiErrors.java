package br.com.bplm.yesbrasil.constants;

import com.google.common.base.Preconditions;

/**
 * Created by lucas on 19/09/15.
 */
public enum ApiErrors {

    CONNECTION_FAILURE(/*R.string.connection_failure*/1, "1"),

    UNKNOW_ERROR(/*R.string.error_default*/1, "2"),

    AUTHENTICATION_FAILURE(/*R.string.authentication_failure*/1, "3"),

    INVALID_DATA(/*R.string.invalid_data*/1, "4"),

    GENERIC_UNIDENTIFIED_API_REQUEST(/*R.string.error_default*/1, "0400"),

    EMAIL_ALREADY_EXISTS(/*R.string.error_default*/1, "EmailAlreadyExists");


    private final String[] codes;
    private final int message;

    private ApiErrors(int message, String... codes) {
        Preconditions.checkNotNull(codes, "Codes can't be null");
        this.codes = codes;
        this.message = message;
    }

    public static ApiErrors errorForCode(String code) {
        if (code == null)
            return GENERIC_UNIDENTIFIED_API_REQUEST;

        for (ApiErrors apiError : ApiErrors.values()) {

            for (String apiErrorsCode : apiError.getCodes()) {

                if (code.equals(apiErrorsCode))
                    return apiError;
            }
        }

        return GENERIC_UNIDENTIFIED_API_REQUEST;
    }


    // GETTER & SETTERs ------------------------------------
    public String[] getCodes() {
        return codes;
    }

    public int getMessage() {
        return message;
    }
}
