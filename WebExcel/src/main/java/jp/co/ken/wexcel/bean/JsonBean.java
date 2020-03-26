package jp.co.ken.wexcel.bean;

public class JsonBean {
	
	private boolean isSuccess = true;
	private int contSeqId;
	private int tableId;
	private String rtnHtml;

	public void setSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public boolean isSuccess() {
        return this.isSuccess;
    }
	
	public int getContSeqId() {
		return contSeqId;
	}

	public void setContSeqId(int contSeqId) {
		this.contSeqId = contSeqId;
	}

	public int getTableId() {
		return tableId;
	}

	public void setTableId(int tableId) {
		this.tableId = tableId;
	}
	
	public String getRtnHtml() {
		return rtnHtml;
	}

	public void setRtnHtml(String rtnHtml) {
		this.rtnHtml = rtnHtml;
	}
}
