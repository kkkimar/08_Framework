package edu.kh.project.myPage.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// @Builder : 빌더 패턴을 이용해 객체 생성 및 초기화를 쉽게 진행
// -> 기본생성자가 생성 안됨
// -> Mybatis 조회 결과를 담을 때 필요한 객체 생성 구문 오류 발생 하므로
// 	 (Mybatis는 기본 생성자로 객체를 만들기 때문)
//  => 기본 생성자 어노테이션 추가!

@Getter
@Setter
@Builder
@NoArgsConstructor // 기본생성자
@AllArgsConstructor // 혹시 모르니 추가
public class UploadFile {
	
	private int fileNo;
	private String filePath;
	private String fileOriginalName;
	private String fileRename;
	private String fileUploadDate;
	private int memberNo;
	
	private String memberNickname;

}
