package com.ben.dwjkd.rxreokbendemo.bean;

/**
 * 服务器返回的公共数据
 * 
 * @author dengweijun
 *
 */
public class JsonBean<T> implements Cloneable {
	/** 返回状态码 */
	private int errorCode;
	/** 消息说明 */
	private String msg;
	/** 是否返回成功 */
	private boolean success;
	/** 数据对象 */
	private T userObject;

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public T getUserObject() {
		return userObject;
	}

	public void setUserObject(T userObject) {
		this.userObject = userObject;
	}

	@Override
	public String toString() {
		return "JsonBean [errorCode=" + errorCode + ", msg=" + msg + ", success=" + success + ", userObject=" + userObject + "]";
	}

	@SuppressWarnings("unchecked")
	@Override
	protected T clone() throws CloneNotSupportedException {
		return (T) super.clone();
	}

}
