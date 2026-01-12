package gymapp.db;

import gymapp.model.Member;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class MemberDbRepository {

    public void save(Member member) {

        String sql = "INSERT INTO members " +
                "(first_name, last_name, gender, membership_type, months, total_price, birth_date, note) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, member.getFirstName());
            ps.setString(2, member.getLastName());
            ps.setString(3, member.getGender());
            ps.setString(4, member.getInfo());
            ps.setInt(5, member.getMonths());
            ps.setInt(6, member.getTotalPrice());
            ps.setString(7, member.getBirthDate().toString());
            ps.setString(8, member.getNote());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
