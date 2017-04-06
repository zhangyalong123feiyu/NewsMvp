package bibi.com.newsmvp.pro.bean;

/**
 * Created by Lenovo_user on 2017/1/7.
 */
public class RoobtResult {
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

    @Override
    public String toString() {
        return "RoobtResult{" +
                "code=" + code +
                ", text='" + text + '\'' +
                '}';
    }
}
