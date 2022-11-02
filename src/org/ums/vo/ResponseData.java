package org.ums.vo;
/**
 * 封装服务器响应的数据 - API 接口 - 礼盒
 * VO(Value Object) : 值对象
 *
 * @Date 2022-10-31
 * @Author zqx
 */
public class ResponseData {
    /**
     *  响应代码（如：200、500、或其它根据业务自定义代码
     */
    private Integer code ;
    /**
     * 响应信息
     */
    private String msg ;
    /**
     * 响应的数据
     */
    private Object data ;

    public ResponseData(){}

    public ResponseData(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
