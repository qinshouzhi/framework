/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: GitlabRepository.java 9552 2015年3月12日 上午11:24:30 WangLijun$
*/
package org.gitlab.api.models; 

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * <p>
 * Title: 
 * </p>
 * <p>
 * Description: 
 * </p>
 * <p>
 * Copyright: Copyright (c) 2015
 * </p>
 * <p>
 * Company: Newtouch
 * </p>
 * 
 * @author WangLijun
 * @version 1.0
 */
public class GitlabRepositoryFile {
	 
	  public static final String URL="/repository/files";
	  @JsonProperty("file_name")
	  private String fileName;
	 
	  private String filePath;
	  @JsonProperty("size")
	  private Long size;
	  @JsonProperty("encoding")
	  private String encoding;
	  @JsonProperty("content")
	  private String content;
	  @JsonProperty("ref")
	  private String ref;
	  @JsonProperty("blob_id")
	  private String blobId;
	  @JsonProperty("commit_id")
	  private String commitId;
	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}
	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	 
	
	
	/**
	 * @return the filePath
	 */
	public String getFilePath() {
		return filePath;
	}
	/**
	 * @param filePath the filePath to set
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	/**
	 * @return the size
	 */
	public Long getSize() {
		return size;
	}
	/**
	 * @param size the size to set
	 */
	public void setSize(Long size) {
		this.size = size;
	}
	/**
	 * @return the encoding
	 */
	public String getEncoding() {
		return encoding;
	}
	/**
	 * @param encoding the encoding to set
	 */
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}
	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * @return the ref
	 */
	public String getRef() {
		return ref;
	}
	/**
	 * @param ref the ref to set
	 */
	public void setRef(String ref) {
		this.ref = ref;
	}
	/**
	 * @return the blobId
	 */
	public String getBlobId() {
		return blobId;
	}
	/**
	 * @param blobId the blobId to set
	 */
	public void setBlobId(String blobId) {
		this.blobId = blobId;
	}
	/**
	 * @return the commitId
	 */
	public String getCommitId() {
		return commitId;
	}
	/**
	 * @param commitId the commitId to set
	 */
	public void setCommitId(String commitId) {
		this.commitId = commitId;
	}
	  
	  
}

	