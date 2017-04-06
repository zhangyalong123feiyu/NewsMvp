package bibi.com.newsmvp.pro.bean;

import java.io.Serializable;

/**
 * Created by bibinet on 2017-1-7.
 */
public class ChatBean implements Serializable {
    private String reason;
    private RoobtResult result;
    private int error_code;

    public ChatBean(RoobtResult result) {
        this.result = result;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public RoobtResult getResult() {
        return result;
    }

    public void setResult(RoobtResult result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    @Override
    public String toString() {
        return "ChatBean{" +
                "reason='" + reason + '\'' +
                ", result=" + result +
                ", error_code=" + error_code +
                '}';
    }
/* public class RoobtResult{
        private int code;
        private String text;

        public RoobtResult(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }
    }*/
}
