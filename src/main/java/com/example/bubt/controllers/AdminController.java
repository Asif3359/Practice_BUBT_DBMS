package com.example.bubt.controllers;

import com.example.bubt.utils.SqlDB;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.awt.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminController {
    @FXML
    private BorderPane AdminBorderPane;
    @FXML
    private AnchorPane AdminAnchorPane;
    @FXML
    private Button btnDashboard;

    @FXML
    private Button btnStudent;

    @FXML
    private Button btnTeacher;

    @FXML
    private Button btnFinance;

    @FXML
    private Button btnDepartment;

    @FXML
    private Button btnNotice;

    @FXML
    private Label CountStudent;

    @FXML
    private Label CountTeacher;

    @FXML
    private StackedBarChart<String, Number> studentYearChart;

    @FXML
    private StackedBarChart<String, Number> assetYearChart;

    @FXML
    protected void onDahsBoardClicked()
    {
        DepartmentBackGroundRemove();
        StudentBackGroundRemove();
        TeacherBackGroundRemove();
        FinanceBackGroundRemove();
        NoticeBackGroundRemove();
        btnDashboard.setStyle("-fx-border-color: white; -fx-background-color:#12542c");
        AdminBorderPane.setCenter(AdminAnchorPane);
    }
    @FXML
    protected void OnStudentClicked()
    {
        DashBoardBackGroundRemove();
        DepartmentBackGroundRemove();
        TeacherBackGroundRemove();
        FinanceBackGroundRemove();
        NoticeBackGroundRemove();
        btnStudent.setStyle("-fx-border-color: white; -fx-background-color:#12542c");
        loadPage("/com/example/bubt/views/Admin-view/AdStudent-view.fxml");
    }
    @FXML
    protected void OnTeacherClicked()
    {
        DashBoardBackGroundRemove();
        DepartmentBackGroundRemove();
        StudentBackGroundRemove();
        FinanceBackGroundRemove();
        NoticeBackGroundRemove();
        btnTeacher.setStyle("-fx-border-color: white; -fx-background-color:#12542c");
        loadPage("/com/example/bubt/views/Admin-view/AdTeacher-view.fxml");
    }
    @FXML
    protected void OnFinanceClicked()
    {
        DashBoardBackGroundRemove();
        DepartmentBackGroundRemove();
        StudentBackGroundRemove();
        TeacherBackGroundRemove();
        NoticeBackGroundRemove();
        btnFinance.setStyle("-fx-border-color: white; -fx-background-color:#12542c");
        loadPage("/com/example/bubt/views/Admin-view/AdFinance-view.fxml");
    }
    @FXML
    protected void OnDepartmentClicked()
    {
        DashBoardBackGroundRemove();
        StudentBackGroundRemove();
        TeacherBackGroundRemove();
        FinanceBackGroundRemove();
        NoticeBackGroundRemove();
        btnDepartment.setStyle("-fx-border-color: white; -fx-background-color:#12542c");
        loadPage("/com/example/bubt/views/Admin-view/AdDepartment-view.fxml");
    }
    @FXML
    protected void OnNoticeClicked()
    {
        DashBoardBackGroundRemove();
        DepartmentBackGroundRemove();
        StudentBackGroundRemove();
        TeacherBackGroundRemove();
        FinanceBackGroundRemove();
        btnNotice.setStyle("-fx-border-color: white; -fx-background-color:#12542c");
        loadPage("/com/example/bubt/views/Admin-view/AdNotice-view.fxml");
    }

    public void  initialize ()
    {
        btnDashboard.setStyle("-fx-border-color: white; -fx-background-color:#25944c");
        //todo
        SqlDB reqDB = new SqlDB();
        Connection connectDB = reqDB.getDatabaseLink();
        String StudentCount = "SELECT count(Role) from usertable where Role='Student'";
        String TeacherCount = "SELECT count(Role) from usertable where Role='Teacher'";
        countInitialize(connectDB, StudentCount , TeacherCount);


        String[] years = {"2019", "2020", "2021", "2022", "2023"};
        int[] totalStudents = {1000, 550, 2000, 1500, 5000};
        initializeStudentYearChart(years, totalStudents);

        String[] AssetYears = {"2019", "2020", "2021", "2022", "2023"};
        int[] totalAsset = {10000, 55550, 255000, 221500, 665000};
        initializeAssetYearChart(AssetYears, totalAsset);

    }

    private void initializeStudentYearChart(String[] years, int[] totalStudents) {

        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Year");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Total Students");

        studentYearChart.setTitle("Total Students by Year");
        studentYearChart.getXAxis().setLabel("Year");
        studentYearChart.getYAxis().setLabel("Total Students");

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Total Students");

        for (int i = 0; i < years.length; i++) {
            series.getData().add(new XYChart.Data<>(years[i], totalStudents[i]));
        }

        studentYearChart.getData().add(series);

    }

    private  void initializeAssetYearChart(String[] years , int[] totalAssets){
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Year");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Total Assets");

        assetYearChart.setTitle("Total Assets by Year");
        assetYearChart.getXAxis().setLabel("Year");
        assetYearChart.getYAxis().setLabel("Total Assets");

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Total Assets");

        for (int i = 0; i < years.length; i++) {
            series.getData().add(new XYChart.Data<>(years[i], totalAssets[i]));
        }

        assetYearChart.getData().add(series);
    }

    private  void countInitialize(Connection connectDB, String StudentCount, String TeacherCount) {
        try {
            PreparedStatement StStatement = connectDB.prepareStatement(StudentCount);
            ResultSet StResultSet = StStatement.executeQuery();

            if (StResultSet.next()) {
                int count = StResultSet.getInt(1);
                CountStudent.setText("Total Student :"+count);
            }

            PreparedStatement TrStatement = connectDB.prepareStatement(TeacherCount);
            ResultSet TrResultSet = TrStatement.executeQuery();
            if (TrResultSet.next()) {
                int count = TrResultSet.getInt(1);
                CountTeacher.setText("Total Teacher :"+count);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    protected void  loadPage(String page) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(page));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        AdminBorderPane.setCenter(root);
    }

    public void DashBoardBackGroundRemove() {
        btnDashboard.setStyle("-fx-border-color: white; -fx-background-color:#25944c");
//        btnDepartment.setStyle("-fx-border-color: white; -fx-background-color:#25944c");
//        btnStudent.setStyle("-fx-border-color: white; -fx-background-color:#25944c");
//        btnTeacher.setStyle("-fx-border-color: white; -fx-background-color:#25944c");
//        btnFinance.setStyle("-fx-border-color: white; -fx-background-color:#25944c");
//        btnNotice.setStyle("-fx-border-color: white; -fx-background-color:#25944c");
    }
    public void DepartmentBackGroundRemove() {
        btnDepartment.setStyle("-fx-border-color: white; -fx-background-color:#25944c");
    }
    public void StudentBackGroundRemove() {
        btnStudent.setStyle("-fx-border-color: white; -fx-background-color:#25944c");
    }
    public  void  TeacherBackGroundRemove() {
        btnTeacher.setStyle("-fx-border-color: white; -fx-background-color:#25944c");
    }
    public  void  FinanceBackGroundRemove() {
        btnFinance.setStyle("-fx-border-color: white; -fx-background-color:#25944c");
    }
    public  void  NoticeBackGroundRemove() {
        btnNotice.setStyle("-fx-border-color: white; -fx-background-color:#25944c");
    }

}
