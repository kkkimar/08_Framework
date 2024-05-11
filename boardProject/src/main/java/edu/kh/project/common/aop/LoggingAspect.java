package edu.kh.project.common.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import edu.kh.project.member.model.dto.Member;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
public class LoggingAspect {

	/** 컨트롤러 수행 전 로그 출력
	 * @param jp 
	 */
	@Before("PointcutBundle.controllerPointCut()")
	public void beforeController(JoinPoint jp) {
		
		//클래스명
		String className = jp.getTarget().getClass().getSimpleName();
		
		//메서드명
		String methodName = jp.getSignature().getName()+"()";
		
		//요청한 클라언트의 HttpServletRequest 객체 얻어오기
		HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		
		//클라이언트 아이피 얻어오기
		String ip = getRemoteAddr(req);
		
		StringBuilder sb = new StringBuilder(); //요즘 많이 사용 (메모리 효율성이 좋음) ********
		
		//로그 모양은 하고싶은 대로 하면 됨 !!! 
		sb.append(String.format("[%s.%s] 요청 / ip : %s", className, methodName, ip));
		
		// 로그인 상태인 경우
		if(req.getSession().getAttribute("loginMember") != null) {
			
			String memberEmail = 
					((Member)req.getSession().getAttribute("loginMember")).getMemberEmail();
			
			sb.append(String.format(", 요청 회원 : %s", memberEmail));
			
		}
		
		log.info(sb.toString());
		
	}
	
	
	
	/** 접속자 아이피 얻어오기
	 * @param request
	 * @return
	 */
	private String getRemoteAddr(HttpServletRequest request) {

		String ip = null;

		ip = request.getHeader("X-Forwarded-For");

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Real-IP");
		}

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-RealIP");
		}

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("REMOTE_ADDR");
		}

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}

		return ip;
	}
	
	
	// -----------------------------------------------------------------------------
	
	// ProceedingJoinPoint
	// - JoinPoint 상속한 자식 객체
	// - @Around에서 사용 가능
	
	// - proceed() 메서드 제공
	// -> proceed() 메서드 호출 전/후로 Before / After가 구분되어짐
	
	// * 주의할 점 *
	// 1) @Around 사용 시 반환형은 Object 타입
	// 2) @Around 메서드 종료시 proceed() 반환값을 return 해야한다
	
	/** 서비스 수행 전/후 동작하는 코드(advice)
	 * @param pjp : 
	 * @return
	 * @throws Throwable 
	 */
	@Around("PointcutBundle.serviceImplPointCut()")
	public Object aroundServiceImpl(ProceedingJoinPoint pjp) throws Throwable {
		
		//@Before 부분
		//클래스명
		String className = pjp.getTarget().getClass().getSimpleName();

		// 메서드명
		String methodName = pjp.getSignature().getName() + "()";
		
		log.info("===== {}.{} 서비스 호출 =====",className,methodName);
		
		//파라미터
		log.info("Parameter : {}", Arrays.toString(pjp.getArgs()));
		
		//서비스 코드 실행 시 시간 기록
		long startMs = System.currentTimeMillis();
		
		Object obj = pjp.proceed(); // 전/후를 나누는 기준점
		
		//@After 부분
		
		//서비스 코드 실행 종료 시간 기록
		long endMs = System.currentTimeMillis();
		
		log.info("Running Time : {}ms", endMs - startMs);
		
		log.info("=================================================");
		
		return obj;
		
		
	}
	
	
	// -----------------------------------------------------------------------------
	
	
	
	/** @Transactional 어노테이션 롤백 동작 후 수행
	 * 사용조건 : 서비스 메서드 레벨로 @Transactional이 작성되어야 동작함
	 * @param jp
	 * @param ex
	 */
	// 예외 발생 후 수행되는 코드
	@AfterThrowing(pointcut = "@annotation(org.springframework.transaction.annotation.Transactional)", 
				   throwing = "ex")
	public void transactionRollback(JoinPoint jp, Throwable ex) {
		log.info("*****트랜잭션이 롤백됨 {} *****",jp.getSignature().getName());
		log.error("[롤백 원인] : {}" , ex.getMessage());
	}
	
	
	
}
