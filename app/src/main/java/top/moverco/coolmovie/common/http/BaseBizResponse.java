package top.moverco.coolmovie.common.http;

/**
 * Created by Moverco.
 */

public class BaseBizResponse {
    public static final int STATE_OK = 200;
    public static final int STATE_INVALID_API_KEY = 401;
    public static final int STATE_REQUESTED_NOT_FOUND = 404;

    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
