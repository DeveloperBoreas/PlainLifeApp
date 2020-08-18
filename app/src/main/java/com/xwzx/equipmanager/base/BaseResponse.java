package com.xwzx.equipmanager.base;

public class BaseResponse<T> {

    /**
     * msg : success
     * code : 0
     */
    private T data;
    private String msg;
    private int status;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "data=" + data +
                ", msg='" + msg + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
