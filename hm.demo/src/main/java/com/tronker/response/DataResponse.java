package com.tronker.response;

import java.io.Serializable;

import cn.tronker.user.error.MainError;
import com.fasterxml.jackson.annotation.JsonView;

/**
 * <br>
 * <b>功能：</b>详细的功能描述<br>
 * <b>作者：</b>文春宏<br>
 * <b>日期：</b> 2015年11月11日 <br>
 * <b>更新者：</b><br>
 * <b>日期：</b> <br>
 * <b>更新内容：</b><br>
 */
public class DataResponse  implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonView(Object.class)
    private Object data = "";

    private String code;
    private String message;
    
    public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public DataResponse() {
        super();
    }

    public DataResponse(MainError mainError) {
        // this.result.setResult("0");
        this.setMessage(mainError.getMessage());
        this.setCode(mainError.getCode());
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
