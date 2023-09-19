package Implement;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;
import javafx.util.Pair;
import java.io.File;
import java.io.FileNotFoundException;

public class DictionaryManagement {

  /**
   * Thêm các từ vào từ điển.
   * @param scan Scanner
   */
  public static void insertFromCommandline(Scanner scan) {
    int type;//Kiểu nhập: 1. Từ stdin.   2. Từ file dictionaries.txt
    String word, meaning;
    System.out.print("Thêm từ\nNhập cách thêm từ\n1. Từ stdin\n2. Từ file dictionaries.txt\nLựa chọn của bạn: ");
    type = Integer.parseInt(scan.nextLine());
    if (type == 1) {
      System.out.print("Nhập số lượng từ muốn thêm vào từ điển: ");
      int num; //Số lượng từ
      num = Integer.parseInt(scan.nextLine());

      System.out.println("Nhập các từ:");

        for (int i = 1; i <= num; i++) {

          System.out.println("\nNhập từ thứ " + i);

          System.out.print("Từ tiếng Anh: ");
          word = WordFormatter.normalize(scan.nextLine()); // Từ tiếng Anh

          System.out.print("Nghĩa tiếng Việt: ");
          meaning = WordFormatter.normalize(scan.nextLine()); // Nghĩa tiếng Việt

          DictionaryMap.add(word, meaning); // Thêm vào Map

        }
      }
    else {
      try {
        String Path = "C:\\Users\\Admin\\Desktop\\Java\\OOP_Project\\src\\main\\resources\\dictionaries.txt";
        File readerObject = new File(Path);
        Scanner fileScan = new Scanner(readerObject);
        while (fileScan.hasNext()) {
          word = WordFormatter.normalize(fileScan.nextLine()); // Từ tiếng Anh
          meaning = WordFormatter.normalize(fileScan.nextLine()); // Nghĩa tiếng Việt
          DictionaryMap.add(word, meaning); // Thêm vào Map
        }
        System.out.println("Đã thêm toàn bộ từ trong file vào từ điển");
      } catch (FileNotFoundException e) {
        System.out.println("Xay ra loi khi mo file");
      }
    }
  }

  /**
   * In ra tất cả các từ có trong từ điển.
   */
  public static void showAllWords() {
    List<Pair<String, String>> ret = DictionaryMap.getAll(); //Lấy ra tất cả các từ trong từ điển
    System.out.println("Danh sách các từ trong từ điển hiện tại:");
    System.out.println("No     | English     | Vietnamese");
    int no = 0;
    for (Pair<String, String> tmp : ret) { //Duyệt từng từ
      String English = WordFormatter.normalize(tmp.getKey());
      String Vietnamese = WordFormatter.normalize(tmp.getValue());
      System.out.printf("%-7d| %-12s| %s%n", ++no, English, Vietnamese);
    }
  }

  /**
   * Xuất ra file tất cả những từ có trong từ điển.
   */
  public static void printAllWords() {
    try {
      String Path = "C:\\Users\\Admin\\Desktop\\Java\\OOP_Project\\src\\main\\resources\\dictionaries_print.txt";
      File file = new File(Path);
      file.createNewFile();
      FileWriter writer = new FileWriter(Path);
      PrintWriter printer = new PrintWriter(writer);
      List<Pair<String, String>> ret = DictionaryMap.getAll(); //Lấy ra tất cả các từ trong từ điển
      printer.println("Danh sách các từ trong từ điển hiện tại:");
      printer.println("No     | English     | Vietnamese");
      int no = 0;
      for (Pair<String, String> tmp : ret) { //Duyệt từng từ
        String English = WordFormatter.normalize(tmp.getKey());
        String Vietnamese = WordFormatter.normalize(tmp.getValue());
        printer.printf("%-7d| %-12s| %s%n", ++no, English, Vietnamese);
      }
      System.out.println("Đã xuất toàn bộ từ điển ra file. Kiểm tra tệp dictionaries_print.txt trong thư mục resources");
      writer.close();
      printer.close();
    } catch (Exception e) {
      System.out.println("Gap van de khi khoi tao file ket qua");
    }
  }

  /**
   * Tra cứu một từ.
   * @param word Từ cần tra
   * @return
   */
  public static String lookUp(String word) {
    return DictionaryMap.lookUp(word);
  }


  /**
   * Tra cứu một từ.
   * @param scan Scanner
   */
  public static void dictionaryLookup(Scanner scan) {
    String str;
    while (true) {
      System.out.println("Hãy nhập vào từ cần tra, hoặc 'EXIT' để thoát");
      str = scan.nextLine();
      if (str.equals("EXIT")) break;
      str = WordFormatter.normalize(str);
      String meaning = lookUp(str);
      if (meaning.equals("NotExist")) {
        System.out.println(str + " không có trong từ điển!");
      }
      else {
        System.out.println(str + ": " + meaning);
      }
    }
  }

  public static void deleteWord(String word) {
    DictionaryMap.delete(word);
    System.out.println("Đã xóa " + word + " khỏi từ điển");
  }
}
