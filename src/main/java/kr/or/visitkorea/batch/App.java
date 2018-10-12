package kr.or.visitkorea.batch;

public class App {

    public static void main(String[] args) {
        Plugin plugin = new ExcelImageCourseMergePlugin();
        plugin.run(args);
        System.out.println("Program Exited Successfully.");
    }
}
