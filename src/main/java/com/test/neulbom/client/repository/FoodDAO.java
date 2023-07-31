package com.test.neulbom.client.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.test.my.DBUtil;

public class FoodDAO {
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;

	public FoodDAO() {
		this.conn = DBUtil.open("180.68.11.121", "hr", "java1234");
	}

	public List<FoodDTO> list() {

		try {

			String sql = "select * from tblfood";

			stat = conn.createStatement();
			rs = stat.executeQuery(sql);

			List<FoodDTO> list = new ArrayList<FoodDTO>();

			while (rs.next()) {

				FoodDTO dto = new FoodDTO();

				

				list.add(dto);
			}

			return list;

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public List<FoodDTO> getFood() {
		try {

			String sql = "select * from tblfood order by food_date desc";

			stat = conn.createStatement();
			rs = stat.executeQuery(sql);

			List<FoodDTO> list = new ArrayList<FoodDTO>();
			int i = 1;

			while (rs.next()) {

				FoodDTO dto = new FoodDTO();

				dto.setFood_seq(rs.getString("food_seq"));
				dto.setDisplayed_seq(i + "");

				String temp = rs.getString("food_date").substring(5, 7);
				if (temp.startsWith("0"))
					temp = temp.substring(1);

				dto.setFood_month(temp);
				dto.setFood_date(rs.getString("food_date"));
				dto.setContent(rs.getString("content"));
				dto.setFood_date(rs.getString("food_date").substring(0, 10));
				dto.setRead(rs.getString("read"));

				list.add(dto);
				i++;
			}

			return list;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	
	
}
