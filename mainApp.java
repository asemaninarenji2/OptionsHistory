import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;
import javafx.application.Application;
import javafx.beans.binding.ObjectExpression;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import java.awt.Component.*;
import java.lang.Object.*;
import java.lang.annotation.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.geometry.*;
import java.util.*;
import java.io.*;
import javafx.collections.*;
import javafx.collections.transformation.*;
import javafx.scene.control.cell.*;
import javafx.beans.property.*;
import javafx.event.*;
import java.sql.*;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class mainApp extends Application
 {
	// new properties of new application
	private Prices priceObj;
	private Options optionObj;
	
	
	
	
	private DBD dbd = new DBD();
	
	private ArrayList<Double> sharePrice= new ArrayList<Double>();
	private ArrayList<Options> optionsListResult = new ArrayList<Options>();
	private String chartTitle = new String();
	
	
	
	public static void main(String [] args)
	{
		Application.launch();
	}

	 public void start(Stage stage)
	 {
	 // Def ine a l a b e l
	//system date time 
	
	
	Label label = new Label("	--------------------------------------------Enter option's details----------------------------------- ");
	Label label1 = new Label("	Stock ID						Price									 ");
	TextField stkID = new TextField();
	TextField price = new TextField();
	Label date = new Label();
	//TextField time = new TextField("time example: 13:24");
	TextArea area = new TextArea();
	Button bt = new Button("Add");
	 
	HBox hb1 = new HBox();
	VBox vb1 = new VBox();
	
	hb1.getChildren().addAll(stkID,price,date);
	vb1.getChildren().addAll(label,label1,hb1,area,bt);
	
	
	//Left side of the windows
	Label labelR = new Label("	-----------------------------------------Enter option's details to find----------------------------------- ");
	Label label1R = new Label("Stock id  						Call-Put			strike price example:41.040	expiry date format:12/10/2021");
	TextField stkIDR = new TextField("");
	TextField optionTypeR = new TextField("");
	TextField exercisePriceR = new TextField("");
	TextField expDateR = new TextField("");
	/*TextArea areaR = new TextArea();
	areaR.setEditable(false);
	areaR.setStyle("-fx-color: grey;");
	*/
	
	
	 /*  TableView tableView = new TableView();

    TableColumn<String, Options> column1 = new TableColumn<>("F1");
    column1.setCellValueFactory(new PropertyValueFactory<>("OptionId"));


    TableColumn<String, Options> column2 = new TableColumn<>("F2");
    column2.setCellValueFactory(new PropertyValueFactory<>("Bid"));


    tableView.getColumns().add(column1);
    tableView.getColumns().add(column2);
	
	

    tableView.getItems().add(new Options("1" ,"2","3","4", "5",6.0,7.0,"8" ));
    tableView.getItems().add(new Person("Jane", "Deer")); */
	
	
	
	TableView tableView = new TableView();
	
	TableColumn<Options,String> column1 = new TableColumn<>("ID");
    column1.setCellValueFactory(new PropertyValueFactory<>("optionId"));
	
	 TableColumn<Options,String> column2 = new TableColumn<Options,String>("Stk");
    column2.setCellValueFactory(new PropertyValueFactory<Options,String>("stkId"));
	
	TableColumn<Options,String> column3 = new TableColumn<Options,String>("Expiry");
    column3.setCellValueFactory(new PropertyValueFactory<Options,String>("expDate"));
	
	TableColumn<Options,String> column4 = new TableColumn<Options,String>("Type");
    column4.setCellValueFactory(new PropertyValueFactory<Options,String>("optionType"));
	
	TableColumn<Options,String> column5 = new TableColumn<Options,String>("SP");
    column5.setCellValueFactory(new PropertyValueFactory<Options,String>("exercisePrice"));
	
	TableColumn<Double, Options> column6 = new TableColumn<>("Bid");
    column6.setCellValueFactory(new PropertyValueFactory<>("bid"));
	
	TableColumn<Double, Options> column7 = new TableColumn<>("Offer");
    column7.setCellValueFactory(new PropertyValueFactory<>("offer"));
	
	
	TableColumn<String, Options> column8 = new TableColumn<>("Added on");
    column8.setCellValueFactory(new PropertyValueFactory<>("dat")); 
	
	
	
	
	
	
	
	tableView.getColumns().add(column1);
	tableView.getColumns().add(column8);
	tableView.getColumns().add(column3);
	tableView.getColumns().add(column4);
	tableView.getColumns().add(column5); 
	tableView.getColumns().add(column6);
	tableView.getColumns().add(column7);
	 tableView.getColumns().add(column2);
	
	
	
	
	
	
	
	Button btR = new Button("Find Option");
	Button btC = new Button("New Search ");
	Button btD = new Button("Show Chart");
	
	
	HBox hb2 = new HBox();
	VBox vb2 = new VBox();//<-----UNDONE
	HBox hb3 = new HBox();
	hb2.getChildren().addAll(stkIDR,optionTypeR,exercisePriceR,expDateR);
	hb3.getChildren().addAll(btR,btC,btD);
	vb2.getChildren().addAll(labelR,label1R,hb2,tableView,hb3);
	
	
	HBox h1root = new HBox(); 
	Label l = new Label("  ");
	h1root.getChildren().addAll(vb1,l,vb2);
	
	HBox h2root = new HBox(); 
	
	Label hl = new Label(" --------------Custom Query------------------ ");
	TextArea queryTxtArea = new TextArea();
	//queryTxtArea.setLineWrap(true);
	Label queryOutput = new Label();
	Button queryButton = new Button("Execute your query");
	VBox bottomLeftVBox = new VBox(hl,queryTxtArea,queryOutput,queryButton);
	
	
	
	
	
	VBox bottomRightVBox = new VBox();
	/*
	
	put all the components the bottom left part of the main screen
	*/
	h2root.getChildren().addAll(bottomLeftVBox,bottomRightVBox);
	VBox root = new VBox();
	root.getChildren().addAll(h1root,h2root);
	
	bt.setOnAction(
					(e) -> {
											try{
													
													
													String msg = new String();
													
													if(stkID.getText().length()==0 || price.getText().length()==0 || area.getText().length()==0)
														throw new Exception("Enter ALL both stock id and price to be added in the database");
													
													// creating Oracle date time format using values of the textFields
													
													
													//making Oracle compatible DATETIME string
													String dat=new String();
													DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");  
   													LocalDateTime now = LocalDateTime.now();
													dat= "TO_DATE('"+  dtf.format(now)  + " ','  DD-MM-YYYY HH24:MI:SS ')";
													
													//price OBJ instantiating
													priceObj = new Prices(stkID.getText().toUpperCase(),dat,Double.parseDouble(price.getText()));
													
													if(dbd.addPriceObj(priceObj).equals("added"))
													{
														stkID.clear();
														price.clear();
														date.setText("   Last Update Time: "+dtf.format(now));
														//time.clear();
														if((msg=dbd.addOptionList(area.getText(),dat)).equals("ok"))//todo
														{
															area.clear();
															
															dat="";
														}
														else
														{
															throw new Exception(msg);
														}
													}
													else
													{
														throw new Exception(msg);
													}
													
												}catch(Exception ex){
												Alert alert = new Alert(Alert.AlertType.ERROR);
												alert.setHeaderText("Error");
												alert.setContentText("MainApp \n update failed\n"+ex.getMessage());
												alert.showAndWait();
											}
											
							}
					);
	btR.setOnAction
	(
		(e) -> 
		{
			try
			{
				
				
				
				sharePrice.clear();
				
				setOptionsListResult(dbd.findOption(stkIDR.getText().toUpperCase(),optionTypeR.getText(),
					exercisePriceR.getText(), expDateR.getText(),sharePrice));
							
				
				
				/*  for(int i=0;i<optionsListResult.size();i++)
				{
					
					tableView.getItems().add(getOptionsListResult(i));
					
				} */
				
				ObservableList<Options> optionTableData = FXCollections.observableArrayList(optionsListResult);
				
				
				tableView.setItems(optionTableData);
				
				
				
				
				
			}catch(Exception ex)
			{
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setHeaderText("Error");
				alert.setContentText("MainApp \n update failed\n"+ex.getMessage());
				alert.showAndWait();
			}
			
			
		}
	
	
	
	);
	
	btC.setOnAction
	(
		(e) -> 
		{
			//areaR.clear();
			stkIDR.clear();
			expDateR.clear();
			exercisePriceR.clear();
			optionTypeR.clear();
			tableView.refresh();
			
			
			
		}
	
	
	
	);
	
	btD.setOnAction
	(
		(e) -> 
		{
				Stage stg = new Stage();
				
				final CategoryAxis xAxis = new CategoryAxis();
				final NumberAxis yAxis = new NumberAxis();
				
				final CategoryAxis xAxis2 = new CategoryAxis();
				final NumberAxis yAxis2 = new NumberAxis();
				
				
				ArrayList<String> cDate= new ArrayList<String>();
				ArrayList<Double> cValue= new ArrayList<Double>();
				
				
				
				for (int i=0;i<optionsListResult.size();i++)
				{
					cValue.add(optionsListResult.get(i).getBid());
					cDate.add(optionsListResult.get(i).getDat());
				}
				stg.setTitle(optionsListResult.get(0).getOptionId()+" Price History");
				//Option prcie Axis ranging
				double max0=cValue.get(0);
				double min0=cValue.get(0);
				
				for(int j=1;j<cValue.size();j++)
				{
					if(cValue.get(j)>max0)
						max0=cValue.get(j);
				}
				for(int j=1;j<cValue.size();j++)
				{
					if(cValue.get(j)<min0)
						min0=cValue.get(j);
				}
				yAxis.setAutoRanging(false);
				yAxis.setLowerBound(min0-0.2);
				yAxis.setUpperBound(max0+0.2);
				
				// Share price Axis ranging
				double max=sharePrice.get(0);
				double min=sharePrice.get(0);
				
				for(int j=1;j<sharePrice.size();j++)
				{
					if(sharePrice.get(j)>max)
						max=sharePrice.get(j);
				}
				for(int j=1;j<sharePrice.size();j++)
				{
					if(sharePrice.get(j)<min){
						min=sharePrice.get(j);
					}
				}
				
				
				
				
				
				yAxis2.setAutoRanging(false);
				yAxis2.setLowerBound(min-0.10);
				yAxis2.setUpperBound(max+0.10);
				//yAxis.setUpperBound(cValue.get(0)+0.05);
				//yAxis2.setLowerBound();
				
				
				xAxis.setLabel("Date Time");       
				
				final LineChart<String,Number> lineChart = 
						new LineChart<String,Number>(xAxis,yAxis);
				final LineChart<String,Number> lineChart2 = 
						new LineChart<String,Number>(xAxis2,yAxis2);
						
				lineChart.setTitle(optionsListResult.get(0).brif());
										
				XYChart.Series series = new XYChart.Series();
				XYChart.Series series2 = new XYChart.Series();
				
				//series.setName(chartTitleGetter());
				series.setName(optionsListResult.get(0).getOptionId()+" Price History");
				series2.setName("Stock");
				
				Data d;
				Data d1;
				for(int i=0;i<cDate.size();i++)
				{
				
				d =new XYChart.Data(cDate.get(i), cValue.get(i));
				d.setNode(createDataNode(d.YValueProperty()));
				series.getData().add(d);
				
				d1 =new XYChart.Data(cDate.get(i), sharePrice.get(i));
				d1.setNode(createDataNode(d1.YValueProperty()));
				series2.getData().add(d1);
				
				}
				
				
				
				
				
				VBox v = new VBox();
				v.getChildren().addAll(lineChart,lineChart2);
				lineChart.getData().add(series);
				lineChart2.getData().add(series2);
				
				Scene scn  = new Scene(v,800,600);
				
			   
				stg.setScene(scn);
				stg.show();
			
		}	
		
	
	
	
	);
	
	
	
	
	queryButton.setOnAction
	(
		(e) -> 
		{
				Stage stg = new Stage();
				stg.setTitle("Your quey result:");
				TextArea result = new TextArea();
				
				VBox v = new VBox();
				v.getChildren().addAll(result);
				
				
				try
				{
					
					result.setText(dbd.query(queryTxtArea.getText()));
				}
				catch (Exception exce)
				{
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setHeaderText("Error");
					alert.setContentText("OracleCONO \n DB update failed\n"+exce.getMessage());
					alert.showAndWait();
				}
				
				
				Scene scn  = new Scene(v,400,300);
				
			   
				stg.setScene(scn);
				stg.show();
			
		}	
		
	
	
	
	);
	
	
	 Scene scene = new Scene(root);

	 // Set the scene f o r the s tage and show i t
	 stage.setScene(scene);
	 stage.setTitle(getClass().getName());
	 stage.show();
	 
			
	 }
	 private static Node createDataNode(ObjectExpression<Number> value) {
        Label label = new Label();
        label.textProperty().bind(value.asString("%,.2f"));

        Pane pane = new Pane(label);
        pane.setShape(new Circle(6.0));
        pane.setScaleShape(false);

        label.translateYProperty().bind(label.heightProperty().divide(-1.5));

        return pane;
    }
	
	public void chartTitleSetter(String cht)
	{
		chartTitle = cht;
	}
	public String chartTitleGetter()
	{
		return chartTitle;
	}
	public void setOptionsListResult(ArrayList<Options> optionsListResult)
	{
		this.optionsListResult=optionsListResult;
	}
	public Options getOptionsListResult(int i)
	{
		return optionsListResult.get(i);
	}
	
	
 }