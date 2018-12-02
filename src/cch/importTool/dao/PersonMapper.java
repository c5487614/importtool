package cch.importTool.dao;

import cch.importTool.model.Person;
import cch.importTool.model.PersonExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PersonMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Person
     *
     * @mbggenerated Sun Dec 02 15:36:57 CST 2018
     */
    int countByExample(PersonExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Person
     *
     * @mbggenerated Sun Dec 02 15:36:57 CST 2018
     */
    int deleteByExample(PersonExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Person
     *
     * @mbggenerated Sun Dec 02 15:36:57 CST 2018
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Person
     *
     * @mbggenerated Sun Dec 02 15:36:57 CST 2018
     */
    int insert(Person record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Person
     *
     * @mbggenerated Sun Dec 02 15:36:57 CST 2018
     */
    int insertSelective(Person record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Person
     *
     * @mbggenerated Sun Dec 02 15:36:57 CST 2018
     */
    List<Person> selectByExample(PersonExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Person
     *
     * @mbggenerated Sun Dec 02 15:36:57 CST 2018
     */
    Person selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Person
     *
     * @mbggenerated Sun Dec 02 15:36:57 CST 2018
     */
    int updateByExampleSelective(@Param("record") Person record, @Param("example") PersonExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Person
     *
     * @mbggenerated Sun Dec 02 15:36:57 CST 2018
     */
    int updateByExample(@Param("record") Person record, @Param("example") PersonExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Person
     *
     * @mbggenerated Sun Dec 02 15:36:57 CST 2018
     */
    int updateByPrimaryKeySelective(Person record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Person
     *
     * @mbggenerated Sun Dec 02 15:36:57 CST 2018
     */
    int updateByPrimaryKey(Person record);
}