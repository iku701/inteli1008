package edu.du.sb1008.dao;


import edu.du.sb1008.dto.SimpleBbsDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ISimpleBbsDao {

//    @Select("select * from simple_bbs order by id desc")
    public List<SimpleBbsDto> listDao();
    public SimpleBbsDto viewDao(String id);
    public int writeDao(String writer, String title, String content);
    public int deleteDao(String id);
    public int updateDao(SimpleBbsDto dto);
    public int resetIdDao();
}
