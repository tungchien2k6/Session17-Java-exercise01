import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MovieManagement manager = new MovieManagement();
        Scanner sc = new Scanner(System.in);

        while (true) {
            printMenu();
            int choice = getChoice(sc);

            try {
                switch (choice) {
                    case 1:
                        addMovieMenu(manager, sc);
                        break;
                    case 2:
                        listMoviesMenu(manager);
                        break;
                    case 3:
                        updateMovieMenu(manager, sc);
                        break;
                    case 4:
                        deleteMovieMenu(manager, sc);
                        break;
                    case 0:
                        System.out.println("Đã thoát chương trình.");
                        sc.close();
                        return;
                    default:
                        System.out.println("Lựa chọn không hợp lệ!");
                }
            } catch (Exception e) {
                System.out.println("Lỗi nhập liệu: " + e.getMessage());
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n=== QUẢN LÝ PHIM (PostgreSQL) ===");
        System.out.println("1. Thêm phim");
        System.out.println("2. Liệt kê phim");
        System.out.println("3. Sửa phim");
        System.out.println("4. Xóa phim");
        System.out.println("0. Thoát");
        System.out.print("Chọn chức năng: ");
    }

    private static int getChoice(Scanner sc) {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (Exception e) {
                System.out.print("Vui lòng nhập số: ");
            }
        }
    }

    private static void addMovieMenu(MovieManagement manager, Scanner sc) {
        System.out.print("Nhập tiêu đề phim: ");
        String title = sc.nextLine();
        System.out.print("Nhập đạo diễn: ");
        String director = sc.nextLine();
        System.out.print("Nhập năm phát hành: ");
        int year = Integer.parseInt(sc.nextLine());

        manager.addMovie(title, director, year);
    }

    private static void listMoviesMenu(MovieManagement manager) {
        manager.listMovies();
    }

    private static void updateMovieMenu(MovieManagement manager, Scanner sc) {
        System.out.print("Nhập ID phim cần sửa: ");
        int id = Integer.parseInt(sc.nextLine());
        System.out.print("Nhập tiêu đề mới: ");
        String title = sc.nextLine();
        System.out.print("Nhập đạo diễn mới: ");
        String director = sc.nextLine();
        System.out.print("Nhập năm phát hành mới: ");
        int year = Integer.parseInt(sc.nextLine());

        manager.updateMovie(id, title, director, year);
    }

    private static void deleteMovieMenu(MovieManagement manager, Scanner sc) {
        System.out.print("Nhập ID phim cần xóa: ");
        int id = Integer.parseInt(sc.nextLine());
        manager.deleteMovie(id);
    }
}