package com.cognizant.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.cognizant.model.Student;

public class StudentDaoImpl implements StudentDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Student> findAll() {
		String query = "select * from Student";
		return jdbcTemplate.query(query, (rs, rowNum) -> new Student(rs.getInt(1), rs.getString(2), rs.getString(3)));

	}

	@Override
	public List<Student> findByName(String name) {

		String sql = "select * from Student where name=?";
		return jdbcTemplate.query(sql, new Object[] { name },
				(rs, rowNum) -> new Student(rs.getInt(1), rs.getString(2), rs.getString(3)));
	}

	@Override
	public int create(Student student) {

		// TODO Auto-generated method stub
		String sql = "Insert into Student(id,name,course) values(?,?,?)";

		int r = jdbcTemplate.update(sql, student.getId(), student.getName(), student.getCourse());
		return r;

	}

	@Override
	public int update(Student student) {
		String sql = "Update Student set name = ?, course=? where id =?";

		int r = jdbcTemplate.update(sql, student.getName(), student.getCourse(), student.getId());
		return r;
	}

	@Override
	public int delete(int id) {
		String sql = "Delete from Student where id =?";

		int r = jdbcTemplate.update(sql, id);
		return r;
	}

	@Override
	public int countStudent() {
		String sql = "select count(*) from Student";

		int r = jdbcTemplate.queryForObject(sql, Integer.class);
		return r;

	}

}
