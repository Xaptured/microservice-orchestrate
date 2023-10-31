package com.esportarena.microservices.orchestrateapi.utilities;

public class StringConstants {

    public static final String ENCRYPTION_PASSWORD = "ENCRYPTION_PASSWORD";
    public static final String ENCRYPTION_ALGORITHM = "ENCRYPTION_ALGORITHM";
    public static final String ENCRYPTION_ITERATIONS = "ENCRYPTION_ITERATIONS";
    public static final String ENCRYPTION_POOL_SIZE = "ENCRYPTION_POOL_SIZE";
    public static final String ENCRYPTION_PROVIDER_NAME = "ENCRYPTION_PROVIDER_NAME";
    public static final String ENCRYPTION_SALT_GENERATOR = "ENCRYPTION_SALT_GENERATOR";
    public static final String ENCRYPTION_OUTPUT_TYPE = "ENCRYPTION_OUTPUT_TYPE";

    public static final String USERNAME = "USERNAME";
    public static final String PASSWORD = "PASSWORD";
    public static final String ROLE = "ROLE";

    public static final String MAIL_SENT_SUCCESSFULLY = "Mail Sent Successfully";
    public static final String ERROR_OCCURRED_SENDING_EMAIL = "Error occurred while sending email";

    public static final String REQUEST_PROCESSED = "Request Processed";

    public static final String VERIFICATION_EMAIL_TEMPLATE = "verification-email-template";
    public static final String VERIFY_YOUR_EMAIL = "Verify your email";

    public static final String INTERNAL_SERVER_ERROR = "Internal Server Error";
    public static final String CLIENT_NOT_EXIST = "Cannot send email as the client id doesn't exist";

    private StringConstants(){}
}
