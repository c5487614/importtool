package cch.importTool.disruptor.file;

public class FileEvent {
	private String fileName;
	private String fileFullPath;
	private String fileType;
	private String importTag;
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileFullPath() {
		return fileFullPath;
	}
	public void setFileFullPath(String fileFullPath) {
		this.fileFullPath = fileFullPath;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public String getImportTag() {
		return importTag;
	}
	public void setImportTag(String importTag) {
		this.importTag = importTag;
	}
	
	

}
