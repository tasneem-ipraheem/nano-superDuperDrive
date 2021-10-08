package com.udacity.jwdnd.course1.cloudstorage.model;

import java.util.Arrays;

//import java.util.Arrays;
//CREATE TABLE IF NOT EXISTS FILES (
//	    fileId INT PRIMARY KEY auto_increment,
//	    filename VARCHAR,
//	    contenttype VARCHAR,
//	    filesize VARCHAR,
//	    userid INT,
//	    filedata BLOB,
//	    foreign key (userid) references USERS(userid)
//	);
/**
 * @author tasne
 *
 */
public class Files {

	    private Integer fileId;
	    private Integer userid;
	    private String filename;
	    private String contenttype;
	    private String filesize;
	    private byte[] filedata;
		
	    public Files() {
			super();
		}
		
//	    public Files(Integer fileId, Integer userid, String filename, String contenttype, String filesize,
//				byte[] filedata) {
//			super();
//			this.fileId = fileId;
//			this.userid = userid;
//			this.filename = filename;
//			this.contenttype = contenttype;
//			this.filesize = filesize;
//			this.filedata = filedata;
//		}

		public Integer getFileId() {
			return fileId;
		}

		public void setFileId(Integer fileId) {
			this.fileId = fileId;
		}

		public Integer getUserid() {
			return userid;
		}

		public void setUserid(Integer userid) {
			this.userid = userid;
		}

		public String getFilename() {
			return filename;
		}

		public void setFilename(String filename) {
			this.filename = filename;
		}

		public String getContenttype() {
			return contenttype;
		}

		public void setContenttype(String contenttype) {
			this.contenttype = contenttype;
		}

		public String getFilesize() {
			return filesize;
		}

		public void setFilesize(String filesize) {
			this.filesize = filesize;
		}

		public byte[] getFiledata() {
			return filedata;
		}

		public void setFiledata(byte[] filedata) {
			this.filedata = filedata;
		}

		@Override
		public String toString() {
			return "Files [fileId=" + fileId + ", userid=" + userid + ", filename=" + filename + ", contenttype="
					+ contenttype + ", filesize=" + filesize + ", filedata=" + Arrays.toString(filedata) + "]";
		}
	    
}
