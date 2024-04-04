package edu.kh.project.main.model;

public interface MainService {

	int inputPw(int inputNo);

	/** 계정 복구
	 * @param inputNo
	 * @return
	 */
	int resetId(int inputNo);

}
