package edu.kh.project.myPage.model.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kh.project.member.model.dto.Member;
import edu.kh.project.myPage.model.mapper.MyPageMapper;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class MyPageServiceImpl implements MyPageService {
	
	
	private final MyPageMapper mapper;
	
	private final BCryptPasswordEncoder bycript;
	
	// @RequiredArgsConstructor 를 이용했을 때 자동 완성 되는 구문
//	@Autowired
//	public MyPageServiceImpl(MyPageMapper mapper) {
//		this.mapper = mapper;
//	}
	
	
	
	// 회원정보 수정 
	@Override
	public int updateInfo(Member inputMember, String[] memberAddress) {
		// 입력된 주소가 있을 경우 
		// memberAddress를 A^^^B^^^C 형태로 가공하기
		
		// 주소 입력 X -> inputMember.getMemberAddress() -> ",,"
		if(inputMember.getMemberAddress().equals(",,")) {
			inputMember.setMemberAddress(null); //주소에 null 대입
		} else { 
			//memberAddress를 A^^^B^^^C 형태로 가공
			String address = String.join("^^^", memberAddress);
			inputMember.setMemberAddress(address); //주소에 가공된 데이터 대입
		}
		
		//SQL 수행 후 결과 반환
		return mapper.updateInfo(inputMember);
	}
	
	
	// 비밀번호 변경
	@Override
	public int changePw(String currentPw, String newPw, Member loginMember) {
		
		// bcrypt 암호화된 비밀번호를 DB에서 조회(회원 번호 필요)
		int memberNo = loginMember.getMemberNo();
		String memberPw = mapper.checkPw(memberNo);
		
		// BcryptPasswordEncoder.matches(평문, 암호화된 비밀번호) 
		
		// 일치할 경우
		int result = 0;
		
		if(bycript.matches(currentPw, memberPw)) {
			/*
			 * -> 새 비밀번호를 암호화 진행
 			   -> 새 비밀번호로 변경(UPDATE)하는 Mapper 호출
    			  회원 번호, 새 비밀번호 -> 하나로 묶음 (Member 또는 Map) 
    		   -> 결과 1 또는 0 반환
			 * */
			
			//새로운 비밀번호 암호화
			String encPw = bycript.encode(newPw);
			//loginMember.setMemberPw(encPw);
			
			Map<String, Object> map = new HashMap<>();
			map.put("encPw", encPw);
			map.put("memberNo", memberNo);

			return result = mapper.changePw(map);
		}
		
		
		return result;
	}
	


}
