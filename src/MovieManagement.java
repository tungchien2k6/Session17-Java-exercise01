import java.sql.*;

public class MovieManagement {
    private static final String URL = "jdbc:postgresql://localhost:5432/Session17-JDBC";
    private static final String USER = "postgres";
    private static final String PASSWORD = "S1mpL0rd";

    // ====================== THÊM PHIM ======================
    public void addMovie(String title, String director, int year) {
        String sql = "CALL add_movie(?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setString(1, title);
            stmt.setString(2, director);
            stmt.setInt(3, year);
            stmt.execute();
            System.out.println("Thêm phim thành công!");

        } catch (SQLException e) {
            System.out.println("Lỗi khi thêm phim: " + e.getMessage());
        }
    }

    // ====================== LIỆT KÊ PHIM ======================
    public void listMovies() {
        String sql = "SELECT * FROM movies ORDER BY id";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("\n=== DANH SÁCH PHIM ===");

            boolean hasData = false;
            while (rs.next()) {
                hasData = true;
                System.out.printf("ID: %-4d | Tiêu đề: %-35s | Đạo diễn: %-25s | Năm: %d%n",
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("director"),
                        rs.getInt("year"));
            }

            if (!hasData) {
                System.out.println("Chưa có phim nào trong cơ sở dữ liệu.");
            }

        } catch (SQLException e) {
            System.out.println("Lỗi khi hiển thị danh sách: " + e.getMessage());
        }
    }

    // ====================== SỬA PHIM ======================
    public void updateMovie(int id, String title, String director, int year) {
        String sql = "CALL update_movie(?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setInt(1, id);
            stmt.setString(2, title);
            stmt.setString(3, director);
            stmt.setInt(4, year);
            stmt.execute();
            System.out.println("Cập nhật phim thành công!");

        } catch (SQLException e) {
            System.out.println("Lỗi khi cập nhật: " + e.getMessage());
        }
    }

    // ====================== XÓA PHIM ======================
    public void deleteMovie(int id) {
        String sql = "CALL delete_movie(?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setInt(1, id);
            stmt.execute();
            System.out.println("Xóa phim thành công!");

        } catch (SQLException e) {
            System.out.println("Lỗi khi xóa: " + e.getMessage());
        }
    }
}