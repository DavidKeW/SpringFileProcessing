	import java.io.ByteArrayInputStream;
	import java.io.FileInputStream;
	import java.io.File;
	import org.apache.commons.io.IOUtils;
	import org.springframework.web.multipart.MultipartFile;
	import org.springframework.mock.web.MockMultipartFile;

	public MultipartFile fileToMultipart(File file){
		FileInputStream input = new FileInputStream(file);
		MultipartFile multipartFile = new MockMultipartFile("file", file.getName(), "text/plain",IOUtils.toByteArray(input));
	}

	public String uploadFile(MultipartFile uploadingFile) throws IllegalStateException, IOException {
		String path = dir + "File_upload\\" + uploadingFile.getOriginalFilename();
		File file = new File(path);
		uploadingFile.transferTo(file);
		return path;
	}

	public void deleteFile(String dirPath) {
		if (null == dirPath)
			return;
		File dir = new File(dirPath);
		if (dir.exists() && dir.isDirectory()) {
			File[] files = dir.listFiles();
			if (null != files) {
				for (File file : files) {
					file.delete();
				}
			}
		}
	}

	public void readFile(MultipartFile multipartFile) throws IOException {
		byte[] bytearr = multipartFile.getBytes();
		ByteArrayInputStream stream = new ByteArrayInputStream(bytearr);
		String myString = IOUtils.toString(stream, "UTF-8");
		System.out.println(myString);
	}

	public String downloadFile(MultipartFile downloadingFile) throws IOException {
		String path = dir + "File_download\\" + downloadingFile.getOriginalFilename();
		File file = new File(path);
		if (!file.exists())
			downloadingFile.transferTo(file);
		return path;
	}