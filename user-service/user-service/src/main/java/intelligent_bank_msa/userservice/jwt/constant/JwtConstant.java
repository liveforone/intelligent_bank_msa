package intelligent_bank_msa.userservice.jwt.constant;

public class JwtConstant {

    public static final String ACCESS_TOKEN = "access-token";
    public static final String REFRESH_TOKEN = "refresh-token";
    public static final String HEADER = "Authorization";
    public static final String CLAIM_NAME = "auth";
    public static final int THIRTY_MINUTE_MS = 1800000;
    public static final String BEARER_TOKEN = "Bearer";
    public static final String NO_AUTH_INFO_TOKEN_MESSAGE = "권한 정보가 없는 토큰입니다.";
    public static final String INVALID_MESSAGE = "Invalid JWT Token";
    public static final String EXPIRED_MESSAGE = "Expired JWT Token";
    public static final String UNSUPPORTED_MESSAGE = "Unsupported JWT Token";
    public static final String EMPTY_MESSAGE = "JWT claims string is empty.";
}
