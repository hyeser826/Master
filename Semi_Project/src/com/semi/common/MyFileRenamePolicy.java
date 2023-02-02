package com.semi.common;

import java.io.File;
import java.util.Date;
import java.text.SimpleDateFormat;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class MyFileRenamePolicy implements FileRenamePolicy {
	public File rename(File originFile) {
		//원본 파일명
		String originName = originFile.getName();
		
		//수정 파일명 : 파일업로드된 시간+5자리랜덤값
		//확장자 : 원본파일의 확장자 그대로
		
		//1. 파일 업로드된 시간 
		String currentTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		
		//2. 5자리 랜덤값
		int ranNum = (int)(Math.random()*90000)+10000; 
		
		//3. 원본파일의 확장자 추출
		//파일명 중간에 .이 있을 가능성도 있으니 맨뒤에서부터 가장 처음 오는 .을 찾아주면 된다.
		//원본 파일명에서 가장 마지막의 .을 찾아 인덱스를 기준으로 확장자를 추출한다.
		String ext = originName.substring(originName.lastIndexOf("."));
		String changeName = currentTime + ranNum + ext;
		
		//돌려줄때도 파일로 돌려준다. 원본파일을 수정된 파일명으로 적용시켜 파일 객체로 반환
		File f = new File(originFile.getParent(),changeName);
		return f;
	}
}
