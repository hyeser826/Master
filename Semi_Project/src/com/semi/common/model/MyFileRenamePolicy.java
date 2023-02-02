package com.semi.common.model;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.oreilly.servlet.multipart.FileRenamePolicy;

//	cos.jar에서 제공하는 FileRenamePolicy 인터페이스를 구현해야한다.
public class MyFileRenamePolicy implements FileRenamePolicy{

//	미완성된 rename메소드 오버라이딩해 구현하기.
//	기존의 파일명을 받아서 파일명 수정작업을 거친 후 수정된 파일을 반환해주는 메소드를 구현해야한다.
	@Override
	public File rename(File originFile) {
		
		//원본파일명
		String originName = originFile.getName();
		
		//수정파일명 : 파일업로드된 시간(년월일시분초) + 5자리 랜덤값(10000~99999)
		//확장자 : 원본파일의 확장자를 그대로
		
		//원본파일명 : asd.jsp -> 수정파일명 : 2022102010483399998.jsp 이런식으로 변경시킬 것이다.
		
		//1. 파일 업로드된 시간(년월일시분초) -> String currentTime;
		String currentTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());//Date는 java.util패키지
		
		//2. 3가지 랜덤값 -> int ranNum;
		int ranNum = (int)(Math.random()*99999)+10000; //
		//Math.random()메소드로 +10000 = 10000부터, *99999 = 99999까지, (int)형변환
		
		//3. 원본파일의 확장자 추출 String ext;
		//파일명 중간에 .이 있을 가능성도 있어 맨 뒤에서부터 가장 처음오는 .을 찾아주면 된다.
		//원본파일명에서 가장 마지막의 .을찾아 인덱스 기준으로 확장자를 추출한다.
		String ext = originName.substring(originName.lastIndexOf("."));
		//substring : 시작 인덱스 부터 잘라수는 메소드를 통해 
		//lastIndexOf 메소드로 "."를 모두 없애준다.
		
		String changeName = currentTime + ranNum + ext;
		
		//원본파일(originFile)을 수정파일 명으로 적용시켜 파일 객체로 반환
		File f = new File(originFile.getParent(), changeName);

		return f;
	}


	

}
