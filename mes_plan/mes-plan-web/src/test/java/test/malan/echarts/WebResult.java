package test.malan.echarts;

import com.github.abel533.echarts.Option;


public class WebResult {
private boolean OK;
private Option data;

public boolean isOK() {
	return OK;
}
public void setOK(boolean oK) {
	OK = oK;
}
public Option getData() {
	return data;
}
public void setData(Option data) {
	this.data = data;
}

private Exception exception;

public Exception getException() {
	return exception;
}
public void setException(Exception exception) {
	this.exception = exception;
}

}
