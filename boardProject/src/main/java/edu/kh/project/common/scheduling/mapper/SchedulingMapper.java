package edu.kh.project.common.scheduling.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SchedulingMapper {

	/** 목록 조회
	 * @return
	 */
	List<String> selectImageList();

}
